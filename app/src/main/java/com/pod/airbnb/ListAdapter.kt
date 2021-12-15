package com.pod.airbnb

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.pod.airbnb.navigation.model.HostingDTO
import kotlinx.android.synthetic.main.activity_hosting.view.*

class ListAdapter(private val context: Context): RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    private var userList = mutableListOf<HostingDTO>()
    private var lodgeList: ArrayList<String> = ArrayList()

    init{
        for (i in 0 until userList.size) {
            lodgeList.add(userList[i].name.toString())
        }
    }

    fun setListData(data:MutableList<HostingDTO>){
        userList = data
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.frag_onboard_accomo_name)
        val addedByUser: TextView = itemView.findViewById(R.id.frag_onboard_user)
//        val fillheart: ImageView = itemView.findViewById(R.id.heart)
        fun bind(hosting: HostingDTO){
            name.text = hosting.name
            addedByUser.text = hosting.addedByUser
//            fillheart.setImageResource(R.drawable.ic_fullheart)

            val pos = adapterPosition
            if(pos != RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, hosting, pos)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_hosting, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    interface OnItemClickListener{
        fun onItemClick(v: View, data: HostingDTO, pos: Int)
    }
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

}