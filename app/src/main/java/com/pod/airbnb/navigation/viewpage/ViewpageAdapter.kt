package com.pod.airbnb.navigation.viewpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pod.airbnb.R
import kotlinx.android.synthetic.main.item.view.*

class ViewpageAdapter(imgList: ArrayList<Int>, titleList: ArrayList<String>, contentList: ArrayList<String>): RecyclerView.Adapter<ViewpageAdapter.PagerViewHolder>(){

    var item_img = arrayListOf<Int>(imgList[0], imgList[2], imgList[4])
    var item_title = arrayListOf<String>(titleList[0], titleList[2], titleList[4])
    var item_content = arrayListOf<String>(contentList[0], contentList[2], contentList[4])
    var item_img_1 = arrayListOf<Int>(imgList[1], imgList[3], imgList[5])
    var item_title_1 = arrayListOf<String>(titleList[1], titleList[3], titleList[5])
    var item_content_1 = arrayListOf<String>(contentList[1], contentList[3], contentList[5])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.frag_img.setImageResource(item_img[position])
        holder.frag_title.setText(item_title[position])
        holder.frag_content.setText(item_content[position])
        holder.frag_img_1.setImageResource(item_img_1[position])
        holder.frag_title_1.setText(item_title_1[position])
        holder.frag_content_1.setText(item_content_1[position])
    }

    override fun getItemCount(): Int = item_img.size


    inner class PagerViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)){

        val frag_img = itemView.frag_onboard_img!!
        val frag_title = itemView.frag_onboard_title!!
        val frag_content = itemView.frag_onboard_content!!

        val frag_img_1 = itemView.frag_onboard_img_1!!
        val frag_title_1 = itemView.frag_onboard_title_1!!
        val frag_content_1 = itemView.frag_onboard_content_1!!
    }

}