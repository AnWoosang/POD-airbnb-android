package com.pod.airbnb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.pod.airbnb.databinding.ActivityChattingBinding
import com.pod.airbnb.navigation.model.ChattingDTO
import com.pod.airbnb.reAdapter.ChatAdapter

class ChattingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChattingBinding
    val db = FirebaseFirestore.getInstance()
    val itemList = arrayListOf<ChattingDTO>()
    val adapter = ChatAdapter(itemList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(R.layout.activity_chatting)

        binding.recycleChat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleChat.adapter = adapter

    }
}
