package com.pod.airbnb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.pod.airbnb.navigation.model.ProfileDTO
import kotlinx.android.synthetic.main.activity_privacy_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.quit
import java.text.SimpleDateFormat

class ProfileActivity : AppCompatActivity() {

    var prof: ProfileDTO? = null
    var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val docRef = firestore?.collection("userProfiles")?.document(auth?.currentUser?.uid.toString())

        if(docRef != null){
            docRef.addSnapshotListener(EventListener<DocumentSnapshot>{ snapshot: DocumentSnapshot?, e: FirebaseFirestoreException? ->
                if(snapshot != null && snapshot.exists()){
                    Log.d("TAG", "Current data:" + snapshot.data)
                }
                prof = snapshot?.toObject(ProfileDTO::class.java)
                hi_name?.text = "안녕하세요, " + prof?.name.toString() + "입니다."
                last_update?.text = "마지막 업데이트 : " + convertTimestampToDate(prof?.timestamp)
            })
        }

        quit.setOnClickListener{
            finish()
        }

        edit.setOnClickListener {
            startActivity(Intent(this, PhotoProfileActivity::class.java))
        }
    }

    fun convertTimestampToDate(timestamp:Long?): String?{
        val sdf = SimpleDateFormat("yyyy년MM월")
        val date = sdf.format(timestamp)
        Log.d("TTTT date", date.toString())
        return date.toString()
    }
}
