package com.pod.airbnb.navigation.viewpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pod.airbnb.R
import kotlinx.android.synthetic.main.item.view.*

class ViewpagerAdapter(imgList: ArrayList<Int>, contentList: ArrayList<String>): RecyclerView.Adapter<ViewpagerAdapter.PagerViewHolder>(){

    var item_img = imgList
    var item_content = contentList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.frag_img.setImageResource(item_img[position])
        holder.frag_content.setText(item_content[position])
    }

    override fun getItemCount(): Int = item_img.size


    inner class PagerViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_2, parent, false)){

        val frag_img = itemView.frag_onboard_img!!
        val frag_content = itemView.frag_onboard_content!!
    }

}