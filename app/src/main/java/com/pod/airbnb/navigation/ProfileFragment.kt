package com.pod.airbnb.navigation

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.pod.airbnb.*
import com.pod.airbnb.navigation.model.ProfileDTO
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_logined_profile.*

class ProfileFragment: Fragment() {

    var prof: ProfileDTO? = null
    var PICK_IMAGE_FROM_ALBUM = 0
    var storage: FirebaseStorage? = null
    var auth: FirebaseAuth? = null
    var firestore:FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()

        if(auth?.currentUser != null){
            view = LayoutInflater.from(activity).inflate(R.layout.fragment_logined_profile, container, false)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        if(auth?.currentUser != null){
            user_name.text = auth?.currentUser?.displayName

            log_out.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                activity?.let{
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }
                activity?.finish()
            }

            pinform.setOnClickListener {
                activity?.let{
                    startActivity(Intent(context, PrivacyProfileActivity::class.java))
                }
            }

            show_prof.setOnClickListener {
                activity?.let{
                    startActivity(Intent(context, ProfileActivity::class.java))
                }
            }
        }
    }


    fun refreshFragment(fragment: Fragment, fragmentManager: FragmentManager?) {
        var ft: FragmentTransaction? = fragmentManager?.beginTransaction()
        ft?.detach(fragment)?.attach(fragment)?.commit()
    }
}