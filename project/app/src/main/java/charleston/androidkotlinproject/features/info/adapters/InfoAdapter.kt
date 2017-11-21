package charleston.androidkotlinproject.features.info.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import charleston.androidkotlinproject.R
import charleston.androidkotlinproject.data.domain.Info
import charleston.androidkotlinproject.extensions.generateImage
import charleston.androidkotlinproject.extensions.isVisible

/**
 * Created by charleston.anjos on 21/11/17.
 */
class InfoAdapter(
        private val context: Context,
        private var list: ArrayList<Info> = arrayListOf(),
        private val itemClickListener: InfoAdapter.ItemClickListener? = null) : RecyclerView.Adapter<InfoAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder? {
        val view = LayoutInflater.from(context).inflate(R.layout.item_info, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder?, position: Int) {
        val item = list[position]
        val viewHolder = holder as ListViewHolder

        viewHolder.run {
            tvOs.text = String.format("Sistema Operacional %s %s", item.os, item.osVersion)
            tvAddress.text = item.address
            tvAppsTitle.isVisible(item.istInstalledApps.isNotEmpty())
            tvApps.text = item.apps()
            image.setImageDrawable(item.os.generateImage())

        }

        itemClickListener?.let {
            viewHolder.itemView.setOnClickListener({
                itemClickListener.onClick(item)
            })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(items: ArrayList<Info>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOs: TextView = itemView.findViewById(R.id.item_info_txt_os)
        val tvAddress: TextView = itemView.findViewById(R.id.item_info_txt_address)
        val tvAppsTitle: TextView = itemView.findViewById(R.id.item_info_txt_apps_title)
        val tvApps: TextView = itemView.findViewById(R.id.item_info_txt_apps)
        val image: ImageView = itemView.findViewById(R.id.item_info_img_icon)
    }

    interface ItemClickListener {
        fun onClick(item: Info)
    }
}