package com.pod.airbnb.navigation

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pod.airbnb.MainActivity
import com.pod.airbnb.R
import com.pod.airbnb.WhiteLoginActivity
import kotlinx.android.synthetic.main.fragment_logined_profile.*
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment: Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_message, container, false)

        auth = Firebase.auth
        if(auth.currentUser != null){
            view = LayoutInflater.from(activity).inflate(R.layout.fragment_logined_message, container, false)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        if(auth.currentUser == null){
            login_mes.setOnClickListener {
                activity?.let{
                    val intent = Intent(context, WhiteLoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

}