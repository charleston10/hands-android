package charleston.androidkotlinproject.features.info.presenters

import charleston.androidkotlinproject.data.domain.Info

/**
 * Created by charleston on 20/11/17.
 */
interface InfoView {
    fun showList(list: ArrayList<Info>)
    fun showLoading(show: Boolean)
    fun showMessage(message: String?)
}