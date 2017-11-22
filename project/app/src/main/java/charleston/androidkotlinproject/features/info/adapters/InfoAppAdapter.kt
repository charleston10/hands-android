package charleston.androidkotlinproject.features.info.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import charleston.androidkotlinproject.R
import charleston.androidkotlinproject.extensions.generateImage

/**
 * Created by charleston.anjos on 21/11/17.
 */
class InfoAppAdapter(
        private val context: Context,
        private var list: List<String> = arrayListOf()) : RecyclerView.Adapter<InfoAppAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder? {
        val view = LayoutInflater.from(context).inflate(R.layout.item_info_detail, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder?, position: Int) {
        val item = list[position]
        val viewHolder = holder as ListViewHolder

        viewHolder.run {
            tvApp.text = item
            image.setImageDrawable(item.first().toString().generateImage())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvApp: TextView = itemView.findViewById(R.id.item_info_detail_txt_app)
        val image: ImageView = itemView.findViewById(R.id.item_info_detail_img_icon)
    }
}