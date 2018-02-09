package com.uludag.can.jibbledevelopertest.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.uludag.can.jibbledevelopertest.R
import com.uludag.can.jibbledevelopertest.listeners.EditPostTitleListener
import com.uludag.can.jibbledevelopertest.models.CombinedData
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter(private var dataList: MutableList<CombinedData>, editPostTitleListener: EditPostTitleListener)
    : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

    private val editPostTitleListener = editPostTitleListener

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.list_item, parent, false)

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        if (holder != null) {
            val data = dataList[position]
            holder.view.tvPostTitle.text = data.post.title
            holder.view.tvPostBody.text = data.post.body

            holder.view.setOnLongClickListener {
                editPostTitleListener.editPostTitle(position)
                return@setOnLongClickListener true
            }
        }
    }

    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val viewForeground: RelativeLayout = view.viewForeground
    }

    // Remove the item form the data list and update the adapter
    fun removeItem(position: Int) {
        if (!dataList.isEmpty()) {
            dataList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

}