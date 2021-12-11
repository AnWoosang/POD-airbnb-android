package com.pod.airbnb

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import com.pod.airbnb.navigation.model.ProfileDTO
import kotlinx.android.synthetic.main.activity_photo_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_logined_profile.*
import java.text.SimpleDateFormat
import java.util.*

class PhotoProfileActivity : AppCompatActivity() {
    var prof: ProfileDTO? = null
    var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var photoUri: Uri? =null
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_profile)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()

        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type ="image/*"
        startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)

        val docRef = firestore?.collection("userProfiles")?.document(auth?.currentUser?.uid.toString())

        if(docRef != null){
            docRef.addSnapshotListener(EventListener<DocumentSnapshot>{ snapshot: DocumentSnapshot?, e: FirebaseFirestoreException? ->
                if(snapshot != null && snapshot.exists()){
                    Log.d("TAG", "Current data:" + snapshot.data)
                }
                prof = snapshot?.toObject(ProfileDTO::class.java)
            })
        }

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)


        addphoto_btn_upload.setOnClickListener {
            contentUpload()
        }
        // 외부사진 가져올 권한 요청

        save_photo_prof.setOnClickListener {

            var profileDTO = ProfileDTO()
            profileDTO.address = address_edittext!!.text.toString()
            profileDTO.job = job_edittext!!.text.toString()
            profileDTO.uid = prof?.uid.toString()
            profileDTO.phone_num = prof?.phone_num.toString()
            profileDTO.sex = prof?.sex.toString()
            profileDTO.timestamp = prof?.timestamp?.toLong()
            profileDTO.email = prof?.email.toString()
            profileDTO.birth = prof?.birth?.toInt()
            profileDTO.photoUri = photoUri.toString()
            profileDTO.name = prof?.name.toString()
            Log.d("TAG1", "Current data:" + profileDTO)
            firestore?.collection("userProfiles")?.document(auth?.currentUser?.uid.toString())?.set(profileDTO)
            Log.d("TAG2", "Current data:" + profileDTO)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_FROM_ALBUM){
            if(resultCode == Activity.RESULT_OK){
                //This is path to the selected image
                photoUri = data?.data

                addphoto_img.setImageURI(photoUri)
            }else{
                //취소버튼을 눌렀을때 작동하는 부분
                finish()
            }
        }
    }

    fun contentUpload(){
        // Make filename
        var timestamp = SimpleDateFormat("yyyyMMddmmss").format(Date())
        var imageFileName = "ProfileImg_" + auth?.currentUser?.email + timestamp + "_.jpg"

        var storageRef = storage?.reference?.child("profileImages")?.child(imageFileName)

        storageRef?.putFile(photoUri!!)?.addOnSuccessListener {
            Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
        }
    }


}
