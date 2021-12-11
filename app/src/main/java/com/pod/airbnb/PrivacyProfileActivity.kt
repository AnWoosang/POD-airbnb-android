package com.pod.airbnb

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.pod.airbnb.navigation.ProfileFragment
import com.pod.airbnb.navigation.model.ProfileDTO
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_privacy_profile.*
import java.text.SimpleDateFormat
import java.util.*

class PrivacyProfileActivity : AppCompatActivity() {

    var prof:ProfileDTO? = null
    var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var auth: FirebaseAuth? = null
    var firestore:FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_profile)

        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val docRef = firestore?.collection("userProfiles")?.document(auth?.currentUser?.uid.toString())

        if(docRef != null){
            docRef.addSnapshotListener(EventListener<DocumentSnapshot>{ snapshot: DocumentSnapshot?, e: FirebaseFirestoreException? ->
                if(snapshot != null && snapshot.exists()){
                    Log.d("TAG", "Current data:" + snapshot.data)
                }
                prof = snapshot?.toObject(ProfileDTO::class.java)

                full_name_edittext.setText(prof?.name.toString())
                sex_edittext.setText(prof?.sex.toString())
                birth_edittext.setText(prof?.birth.toString())
                pro_email_edittext.setText(prof?.email.toString())
                phonenum_edittext.setText(prof?.phone_num.toString())
            })
        }

        quit_edit.setOnClickListener{
            finish()
        }

        save_profile.setOnClickListener {
            profileUpload()
            finish()
        }
    }


    fun profileUpload(){
        val user = auth?.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = full_name_edittext.text.toString()
            if (user?.email == null){
                user?.updateEmail(pro_email_edittext.text.toString())
            }
        }
        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    Log.d("TAG", "User profile updated.")
                }
            }
        var userName = full_name_edittext.text.toString()
        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var storageRef = storage?.reference?.child("userProfiles")?.child(userName)

        var profileDTO = ProfileDTO()

        // 사용자 번호 추가
        profileDTO.uid = user?.uid
        // 이름 추가
        profileDTO.birth = birth_edittext!!.text.toString().toInt()
        // 이메일 추가
        profileDTO.email = pro_email_edittext!!.text.toString()
        // 이름 추가
        profileDTO.name = full_name_edittext!!.text.toString()
        // 전화번호 추가
        profileDTO.phone_num = phonenum_edittext!!.text.toString()
        // 성별 추가
        profileDTO.sex = sex_edittext!!.text.toString()
        // 마지막 수정날짜
        profileDTO.timestamp = System.currentTimeMillis()

        firestore?.collection("userProfiles")?.document(user?.uid.toString())?.set(profileDTO)
        setResult(Activity.RESULT_OK)
    }

    fun convertTimestampToDate(timestamp:Long?): String{
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일")
        val date = sdf.format(timestamp)
        return date.toString()
    }

}