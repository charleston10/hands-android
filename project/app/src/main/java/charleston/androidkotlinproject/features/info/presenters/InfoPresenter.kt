package charleston.androidkotlinproject.features.info.presenters

import android.annotation.SuppressLint
import android.support.annotation.IntDef
import charleston.androidkotlinproject.data.domain.Info
import charleston.androidkotlinproject.data.remote.features.info.InfoManager
import charleston.androidkotlinproject.di.AppInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.util.*
import javax.inject.Inject

/**
 * Created by charleston on 20/11/17.
 */
class InfoPresenter(private val view: InfoView) {

    companion object {
        const val SORT_SO_ASC = 0
        const val SORT_SO_DESC = 1
    }

    /**
     * Create annotation for sort type
     */
    @IntDef(SORT_SO_ASC.toLong(), SORT_SO_DESC.toLong())
    @Retention(RetentionPolicy.SOURCE)
    annotation class SortType

    @Inject
    lateinit var manager: InfoManager

    lateinit var list: ArrayList<Info>

    init {
        AppInjector.dagger.inject(this)
    }

    fun findAll() {
        view.showLoading(true)

        manager.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe({ result ->
                    list = result
                    view.showLoading(false)
                    view.showList(result)
                }, { error ->
                    view.showMessage(error.message)
                    error.printStackTrace()
                })
    }

    fun showAll() {
        view.showList(list)
    }

    fun filter(value: String) {
        val listFiltered: ArrayList<Info> = arrayListOf()

        if (list.isNotEmpty()) {
            for (info: Info in list) {
                when {
                    info?.batteryState != null && info?.batteryState?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.os != null && info?.os?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.osVersion != null && info?.osVersion?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.category != null && info?.category?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.city != null && info?.city?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.country != null && info?.country?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.address != null && info?.address?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.state != null && info?.state?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.venueName != null && info?.venueName?.toUpperCase().contains(value.toUpperCase()) -> listFiltered.add(info)
                    info?.istInstalledApps != null && filterApps(info, value) -> listFiltered.add(info)
                }
            }
        }

        view.showList(listFiltered)
    }

    @SuppressLint("SwitchIntDef")
    fun sort(@SortType sortType: Int) {
        when (sortType) {
            SORT_SO_ASC -> Collections.sort(list, { o1, o2 ->
                if (o1?.osVersion != null && o2?.osVersion != null) {
                    o2.osVersion.toUpperCase().compareTo(o1.osVersion.toUpperCase())
                } else {
                    0
                }
            })
            SORT_SO_DESC -> Collections.sort(list, { o1, o2 ->
                if (o1?.osVersion != null && o2?.osVersion != null) {
                    o1.osVersion.toUpperCase().compareTo(o2.osVersion.toUpperCase())
                } else {
                    0
                }
            })
        }
        view.showList(list)
    }

    private fun filterApps(info: Info, value: String): Boolean {
        info.istInstalledApps
                .filter { it.toUpperCase().contains(value.toUpperCase()) }
                .forEach { return true }

        return false
    }
}