package com.pod.airbnb.reAdapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pod.airbnb.R
import com.pod.airbnb.navigation.model.ChattingDTO
import kotlinx.android.synthetic.main.activity_hosting.view.*

class ChatroomAdapter(val currentRoomName:String, val roomList:ArrayList<String>): RecyclerView.Adapter<ChatroomAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val roomName: TextView = itemView.findViewById(R.id.chatroom_name)

        fun bind(chat: String){
            roomName.text = chat
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chatroom, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(roomList[position])
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

}