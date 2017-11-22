package charleston.androidkotlinproject.features.info.apresentations

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.*
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
class InfoActivity : AppCompatActivity(), InfoView, InfoAdapter.ItemClickListener, PopupMenu.OnMenuItemClickListener {

    private val toolbar by lazy { findViewById<AppBarLayout>(R.id.toolbar_layout) }
    private val imgSort by lazy { findViewById<ImageView>(R.id.main_img_sort) }
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
        bindSort()

        presenter.findAll()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        onUserInteraction = true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_pop_up_info_sort_so_asc -> {
                presenter.sort(InfoPresenter.SORT_SO_ASC)
                return true
            }
            R.id.menu_pop_up_info_sort_so_desc -> {
                presenter.sort(InfoPresenter.SORT_SO_DESC)
                return true
            }
        }
        return false
    }

    override fun onClick(item: Info) = startActivity(InfoDetailActivity.getStartIntent(this, item))

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

    private fun bindSort() {
        imgSort.setOnClickListener { showPopUp(it) }
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

    private fun showPopUp(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_popup_info_sort)
        popupMenu.setOnMenuItemClickListener(this)
        popupMenu.show()
    }
}