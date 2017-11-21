package charleston.androidkotlinproject

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import charleston.androidkotlinproject.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject
import android.support.multidex.MultiDex


/**
 * Created by charleston.anjos on 03/10/17.
 */
class App : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidInjectorService: DispatchingAndroidInjector<Service>

    private var isUnderUnitTest: Boolean = false

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)

        try {
            MultiDex.install(this)
        } catch (multiDexException: RuntimeException) {
            isUnderUnitTest = try {
                val robolectric = Class.forName("org.robolectric.Robolectric")
                robolectric != null
            } catch (e: ClassNotFoundException) {
                false
            }

            if (!isUnderUnitTest) {
                throw multiDexException
            }
        }
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun serviceInjector(): DispatchingAndroidInjector<Service>? {
        return dispatchingAndroidInjectorService
    }
}