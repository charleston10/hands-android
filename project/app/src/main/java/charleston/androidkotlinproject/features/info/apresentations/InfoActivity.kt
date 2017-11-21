package charleston.androidkotlinproject.features.info.apresentations

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import charleston.androidkotlinproject.data.domain.Info
import charleston.androidkotlinproject.features.info.presenters.InfoPresenter
import charleston.androidkotlinproject.features.info.presenters.InfoView

/**
 * Created by charleston on 20/11/17.
 */
class InfoActivity : AppCompatActivity(), InfoView {

    private val presenter: InfoPresenter by lazy { InfoPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.findAll()
    }

    override fun showList(list: List<Info>) {
        //DO NOTHING
    }

    override fun showLoading(show: Boolean) {
        //DO NOTHING
    }
}