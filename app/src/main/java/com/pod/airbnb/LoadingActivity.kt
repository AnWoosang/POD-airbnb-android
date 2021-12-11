package com.pod.airbnb

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.pod.airbnb.navigation.model.ProfileDTO

class LoadingActivity : AppCompatActivity() {

    var prof: ProfileDTO? = null
    var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()

        val user = auth?.currentUser
        if(user?.photoUrl == null){
            val profileUpdates = userProfileChangeRequest {
                photoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/airbnb-clone-dodream.appspot.com/o/profileImages%2Fimg_default.jpg?alt=media&token=77ca1216-8eda-49fe-9a52-258498e6a8cf")
            }
            Log.d("TAG", "Current User" + user?.photoUrl)
            user?.updateProfile(profileUpdates)
        }

        startLoading()
    }

    private fun startLoading(){
        val handler = Handler()
        handler.postDelayed({finish()}, 2000)
    }
}
