package charleston.androidkotlinproject.di.modules

import charleston.androidkotlinproject.features.info.apresentations.InfoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by charleston.anjos on 03/10/17.
 */
@Module
internal abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf())
    internal abstract fun contribuiteInfoActivity(): InfoActivity

}