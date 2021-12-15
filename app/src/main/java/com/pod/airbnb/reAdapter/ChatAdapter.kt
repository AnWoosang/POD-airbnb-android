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

class ChatAdapter(val chatList:ArrayList<ChattingDTO>): RecyclerView.Adapter<ChatAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val senderName: TextView = itemView.findViewById(R.id.chatroom_title)
        val created: TextView = itemView.findViewById(R.id.chatroom_time)
        val content: TextView = itemView.findViewById(R.id.chatroom_content)

        fun bind(chat: ChattingDTO){
            senderName.text = chat.thread!!.senderName
            content.text = chat.thread!!.content
            created.text = chat.thread!!.created.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

}