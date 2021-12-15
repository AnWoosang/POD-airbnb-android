package com.pod.airbnb.navigation

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.gamingservices.GameRequestDialog.show
import com.google.api.Distribution
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.pod.airbnb.MainActivity
import com.pod.airbnb.R
import com.pod.airbnb.WhiteLoginActivity
import com.pod.airbnb.databinding.AlertdialogEdittextBinding
import com.pod.airbnb.navigation.model.ChattingDTO
import com.pod.airbnb.reAdapter.ChatroomAdapter
import kotlinx.android.synthetic.main.alertdialog_edittext.*
import kotlinx.android.synthetic.main.fragment_logined_message.*
import kotlinx.android.synthetic.main.fragment_logined_message.view.*
import kotlinx.android.synthetic.main.fragment_logined_profile.*
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment: Fragment() {


    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    private lateinit var adapter: ChatroomAdapter
    private val chatroomList = arrayListOf<String>()
    private lateinit var currentRoom: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_message, container, false)
        if (auth.currentUser != null){
            view = LayoutInflater.from(activity).inflate(R.layout.fragment_logined_message, container, false)
        }

//        view.chatrooms.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        adapter = ChatroomAdapter(editText.text.toString(), chatroomList)
//        view.chatrooms.adapter = adapter
//
//
//        auth = Firebase.auth
//        if(auth.currentUser != null){
//            view = LayoutInflater.from(activity).inflate(R.layout.fragment_logined_message, container, false)
//            chatroom_make.setOnClickListener {
//                val builder = AlertDialog.Builder(requireContext())
//                val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
//                val editText = builderItem.editText
//                var cDTO = ChattingDTO()
//                with(builder){
//                    setTitle("채팅방 이름")
//                    setView(builderItem.root)
//                    setPositiveButton("OK"){ dialogInterface: DialogInterface, i: Int ->
//                        if(editText.text != null){
//                            cDTO.name = editText.text.toString()
//
//                            db.collection("channels")
//                                .add(cDTO)
//                                .addOnSuccessListener {
//                                    Log.d("chatroom", "success")
//                                    chatroomList.add(cDTO.name.toString())
//                                }
//
//                            adapter.notifyDataSetChanged()
//                        }
//                    }
//                    show()
//                }
//
//            }
//        } else{
//            login_mes.setOnClickListener {
//                activity?.let{
//                    val intent = Intent(context, WhiteLoginActivity::class.java)
//                    startActivity(intent)
//                }
//            }
//        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        val docRef = db.collection("channels")
//        docRef.addSnapshotListener{ snapshot, e ->
//            if(e != null){
//                Log.w("Tag", "Listened failed", e)
//                return@addSnapshotListener
//            }
//
//            if (snapshot != null && !snapshot.isEmpty){
//
//            }
//
//            for (i in snapshot!!){
//                i.getString("name")?.let{
//                    currentRoom = it
//                    chatroomList.add(it)
//                }
//            }
//            Log.d("TAG", "Current cities in CA: $currentRoom")
//        }
//
//        adapter.notifyDataSetChanged()
    }

}