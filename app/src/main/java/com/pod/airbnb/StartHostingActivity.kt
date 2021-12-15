package com.pod.airbnb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import com.pod.airbnb.navigation.model.ContentsDTO
import com.pod.airbnb.navigation.model.HostingDTO
import com.pod.airbnb.navigation.model.ProfileDTO
import kotlinx.android.synthetic.main.activity_start_hosting.*
import kotlinx.android.synthetic.main.fragment_logined_profile.*

class StartHostingActivity : AppCompatActivity() {

    var prof : ProfileDTO? = null
    var hostingActivity = HostingDTO()
//    var contentsDTO = ContentsDTO()
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null



    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_hosting)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        getUserProfile()


        dialog_guests_num.setOnClickListener {
            getGuestsNum()
        }

        dialog_type.setOnClickListener {
            getType()
        }

        dialog_scope.setOnClickListener{
            getScope()
        }
        submit_hosting.setOnClickListener {
//            contentsDTO.user_name = prof?.name.toString()
//            contentsDTO.uid = prof?.uid.toString()
//            contentsDTO.email = prof?.email.toString()
//            contentsDTO.timestamp = System.currentTimeMillis()
//            contentsDTO.accom_name= accom_name.text.toString()
//            contentsDTO.accom_address = accom_add.text.toString()
//            contentsDTO.accom_fee = accom_fee.text.toString().toInt()
//            contentsDTO.accom_scope = dialog_scope.text.toString()
//            contentsDTO.accom_type = dialog_type.text.toString()
//
//            firestore?.collection("hosting")?.document(auth?.currentUser?.displayName.toString())?.set(contentsDTO)

            hostingActivity.addedByUser = auth!!.currentUser?.email.toString()
            hostingActivity.description = accom_descript.text.toString()
            hostingActivity.name = accom_name.text.toString()
            hostingActivity.address = accom_add.text.toString()

            Log.d("TAG", "숙소 이름 : " + hostingActivity.name.toString() + ", 호스트 이메일 : " + hostingActivity.addedByUser.toString() + ", 숙소 소개 : " + hostingActivity.description.toString())
            databaseReference.child("lodging-items").push().setValue(hostingActivity);


            startActivity(Intent(this, HostingActivity::class.java))
            finish()
        }
    }

    fun getGuestsNum() {
        val ListItems: MutableList<String> = ArrayList()

        for (i in 1..12){
            ListItems.add(i.toString() + " 명")
        }
        val items: Array<String> = ListItems.toTypedArray<String>()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("게스트 수")
        builder.setItems(items) { dialog, pos ->
            val selectedText = items[pos].toString()
            dialog_guests_num.text = selectedText
        }
        builder.show()
    }

    fun getType() {
        val ListItems: MutableList<String> = ArrayList()
        ListItems.add("아파트")
        ListItems.add("주택")
        ListItems.add("별채")
        ListItems.add("독특한 숙소")
        ListItems.add("B&B")
        ListItems.add("부티크 호텔")
        val items: Array<String> = ListItems.toTypedArray<String>()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("숙소 유형")
        builder.setItems(items) { dialog, pos ->
            val selectedText = items[pos].toString()
            dialog_type.text = selectedText
        }
        builder.show()
    }

    fun getScope() {
        val ListItems: MutableList<String> = ArrayList()
        ListItems.add("공간 전체")
        ListItems.add("개인실")
        ListItems.add("다인실")
        val items: Array<String> = ListItems.toTypedArray<String>()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("이용 범위")
        builder.setItems(items) { dialog, pos ->
            val selectedText = items[pos].toString()
            dialog_scope.text = selectedText
        }
        builder.show()
    }


    fun getUserProfile(){
        val docRef = firestore?.collection("userProfiles")?.document(auth?.currentUser?.uid.toString())

        if(docRef != null){
            docRef.addSnapshotListener(EventListener<DocumentSnapshot>{ snapshot: DocumentSnapshot?, e: FirebaseFirestoreException? ->
                if(snapshot != null && snapshot.exists()){
                    Log.d("TAG", "Current data:" + snapshot.data)
                }
                prof = snapshot?.toObject(ProfileDTO::class.java)
            })
        }

    }
}
