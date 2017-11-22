package charleston.androidkotlinproject.features.info.apresentations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import charleston.androidkotlinproject.R
import charleston.androidkotlinproject.data.domain.Info
import charleston.androidkotlinproject.extensions.isVisible
import charleston.androidkotlinproject.features.info.adapters.InfoAppAdapter
import kotlinx.android.synthetic.main.activity_info_detail.*

/**
 * Created by charleston on 21/11/17.
 */
class InfoDetailActivity : AppCompatActivity() {

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

        info_detail_img_back.setOnClickListener { onBackPressed() }
    }

    private fun bindInfo() {
        info.address?.let { info_detail_txt_address.text = it }
        info.deviceModel?.let { info_detail_txt_device_model.text = it }
    }

    private fun bindList() {
        if (info.istInstalledApps.isNotEmpty()) {
            info_detail_rv_apps.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            info_detail_rv_apps.setHasFixedSize(true)
            info_detail_rv_apps.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            info_detail_rv_apps.adapter = InfoAppAdapter(this, info.istInstalledApps)
        } else {
            info_detail_rv_apps.isVisible(false)
            info_detail_txt_empty.isVisible(true)
        }
    }
}
