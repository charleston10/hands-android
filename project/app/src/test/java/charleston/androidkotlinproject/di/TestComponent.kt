package charleston.androidkotlinproject.di

import charleston.androidkotlinproject.InfoTest
import charleston.androidkotlinproject.di.modules.ApiModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by charleston on 20/11/17.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, ApiModule::class))
interface TestComponent  {
    fun inject(test: InfoTest)
}