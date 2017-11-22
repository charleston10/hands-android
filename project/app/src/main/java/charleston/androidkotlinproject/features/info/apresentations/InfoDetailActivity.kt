package charleston.androidkotlinproject.features.info.apresentations

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import charleston.androidkotlinproject.R
import charleston.androidkotlinproject.data.domain.Info
import charleston.androidkotlinproject.extensions.create
import charleston.androidkotlinproject.extensions.isVisible
import charleston.androidkotlinproject.features.info.adapters.InfoAdapter
import charleston.androidkotlinproject.features.info.adapters.InfoAppAdapter

/**
 * Created by charleston on 21/11/17.
 */
class InfoDetailActivity : AppCompatActivity() {

    private val imgBack by lazy { findViewById<ImageView>(R.id.info_detail_img_back) }
    private val txtEmpty by lazy { findViewById<TextView>(R.id.info_detail_txt_empty) }
    private val txtAddress by lazy { findViewById<TextView>(R.id.info_detail_txt_address) }
    private val txtDeviceModel by lazy { findViewById<TextView>(R.id.info_detail_txt_device_model) }

    private val rvList by lazy { findViewById<RecyclerView>(R.id.info_detail_rv_apps) }

    private lateinit var info: Info

    companion object {
        private val EXTRA_INFO = "EXTRA_INFO"

        fun getStartIntent(context: Context, info: Info): Intent {
            val bundle = Bundle().apply {
                putSerializable(EXTRA_INFO, info)
            }

            return Intent(context, InfoDetailActivity::class.java)
                    .putExtras(bundle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_detail)

        intent.extras?.let {
            info = it.getSerializable(EXTRA_INFO) as Info

            bindInfo()
            bindList()
        }

        imgBack.setOnClickListener { onBackPressed() }
    }

    private fun bindInfo() {
        info.address?.let { txtAddress.text = it }
        info.deviceModel?.let { txtDeviceModel.text = it }
    }

    private fun bindList() {
        if (info.istInstalledApps.isNotEmpty()) {
            rvList.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            rvList.setHasFixedSize(true)
            rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            rvList.adapter = InfoAppAdapter(this, info.istInstalledApps)
        } else {
            rvList.isVisible(false)
            txtEmpty.isVisible(true)
        }
    }
}
