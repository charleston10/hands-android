package charleston.androidkotlinproject.features.info.presenters

import charleston.androidkotlinproject.data.remote.features.info.InfoManager
import charleston.androidkotlinproject.di.AppInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by charleston on 20/11/17.
 */
class InfoPresenter(private val view: InfoView) {

    @Inject
    lateinit var manager: InfoManager

    init {
        AppInjector.dagger.inject(this)
    }

    fun findAll() {
        view.showLoading(true)

        manager.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe {
                    view.showLoading(false)
                    view.showList(it)
                }
    }

    fun findById(id: String) {
        manager.findById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe {

                }
    }
}