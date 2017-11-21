package charleston.androidkotlinproject.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

/**
 * Created by charleston.anjos on 03/10/17.
 */
@Module
class AppModule(internal val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun providerApplicationContext(): Context {
        return application.applicationContext
    }
}