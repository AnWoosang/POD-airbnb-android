package com.pod.airbnb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pod.airbnb.navigation.model.HostingDTO

val HOSTING = 0
val WISHLIST = 1
val TRAVEL = 2

// Data source를 캡슐화 해줌
class Repo {
    fun getData(): LiveData<MutableList<HostingDTO>>{
        val mutableData = MutableLiveData<MutableList<HostingDTO>>()
        val database = Firebase.database
        val myRef = database.getReference("lodging-items")

        myRef.addValueEventListener(object: ValueEventListener{
            val listData: MutableList<HostingDTO> = mutableListOf<HostingDTO>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(HostingDTO::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return mutableData
    }

}