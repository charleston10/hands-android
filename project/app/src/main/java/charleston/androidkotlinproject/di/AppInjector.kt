package charleston.androidkotlinproject.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import charleston.androidkotlinproject.App
import charleston.androidkotlinproject.di.components.DaggerAppComponent
import charleston.androidkotlinproject.di.modules.AppModule
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by charleston.anjos on 03/10/17.
 */
object AppInjector {

    lateinit var dagger: DaggerAppComponent

    fun init(application: App) {

        dagger = DaggerAppComponent.builder()
                .application(application)
                .appModule(AppModule(application))
                .build() as DaggerAppComponent

        dagger.inject(application)

        application
                .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                        handleActivity(activity)
                    }

                    override fun onActivityStarted(activity: Activity) {}

                    override fun onActivityResumed(activity: Activity) {}

                    override fun onActivityPaused(activity: Activity) {}

                    override fun onActivityStopped(activity: Activity) {}

                    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

                    override fun onActivityDestroyed(activity: Activity) {}
                })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentCreated(fm: FragmentManager?, fragment: Fragment?,
                                                               savedInstanceState: Bundle?) {
                                    if (fragment is Injectable) {
                                        AndroidSupportInjection.inject(fragment)
                                    }
                                }
                            }, true
                    )
        }
    }
}