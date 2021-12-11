package com.pod.airbnb.navigation.viewpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pod.airbnb.R
import kotlinx.android.synthetic.main.item.view.*

class ViewpageAdapt(imgList: ArrayList<Int>, titleList: ArrayList<String>, contentList: ArrayList<String>): RecyclerView.Adapter<ViewpageAdapt.PagerViewHolder>(){

    var item_img = imgList
    var item_title = titleList
    var item_content = contentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.frag_img.setImageResource(item_img[position])
        holder.frag_title.setText(item_title[position])
        holder.frag_content.setText(item_content[position])
    }

    override fun getItemCount(): Int = item_img.size


    inner class PagerViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_3, parent, false)){

        val frag_img = itemView.frag_onboard_img!!
        val frag_title = itemView.frag_onboard_title!!
        val frag_content = itemView.frag_onboard_content!!
    }

}