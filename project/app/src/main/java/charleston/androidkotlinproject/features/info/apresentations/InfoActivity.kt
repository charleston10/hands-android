package charleston.androidkotlinproject.features.info.apresentations

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import charleston.androidkotlinproject.R
import charleston.androidkotlinproject.data.domain.Info
import charleston.androidkotlinproject.extensions.isVisible
import charleston.androidkotlinproject.features.info.adapters.InfoAdapter
import charleston.androidkotlinproject.features.info.presenters.InfoPresenter
import charleston.androidkotlinproject.features.info.presenters.InfoView
import com.jakewharton.rxbinding.widget.RxTextView
import java.util.concurrent.TimeUnit

/**
 * Created by charleston on 20/11/17.
 */
class InfoActivity : AppCompatActivity(), InfoView, InfoAdapter.ItemClickListener {

    private val toolbar by lazy { findViewById<AppBarLayout>(R.id.toolbar_layout) }
    private val loading by lazy { findViewById<ProgressBar>(R.id.main_pb_loading) }
    private val txtFilter by lazy { findViewById<EditText>(R.id.main_txt_filter) }

    private val rvList by lazy { findViewById<RecyclerView>(R.id.main_rv_list) }
    private var adapter = InfoAdapter(context = this, itemClickListener = this)

    private val presenter: InfoPresenter by lazy { InfoPresenter(this) }

    private var onUserInteraction = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindList()
        bindFind()

        presenter.findAll()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        onUserInteraction = true
    }

    override fun onClick(item: Info) {
        //DO NOTHING
    }

    override fun showList(list: ArrayList<Info>) {
        runOnUiThread {
            adapter.addItems(list)
            rvList.isVisible(true)
            toolbar.isVisible(true)
        }
    }

    override fun showLoading(show: Boolean) {
        loading.isVisible(show)
    }

    override fun showMessage(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindList() {
        rvList.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvList.setHasFixedSize(true)
        rvList.adapter = adapter
    }

    private fun bindFind() {
        RxTextView.textChanges(txtFilter)
                .filter { it.isEmpty() && onUserInteraction }
                .debounce(600, TimeUnit.MILLISECONDS)
                .map<String> { charSequence -> charSequence.toString() }
                .subscribe { presenter.showAll() }

        RxTextView.textChanges(txtFilter)
                .filter { it.isNotEmpty() }
                .debounce(600, TimeUnit.MILLISECONDS)
                .map<String> { charSequence -> charSequence.toString() }
                .subscribe { presenter.filter(it) }
    }
}