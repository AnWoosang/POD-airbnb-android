package com.pod.airbnb.navigation

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.api.Distribution
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.pod.airbnb.ListAdapter
import com.pod.airbnb.ListViewModel
import com.pod.airbnb.MainActivity
import com.pod.airbnb.R
import com.pod.airbnb.navigation.model.HostingDTO
import kotlinx.android.synthetic.main.fragment_logined_wishlist.*
import kotlinx.android.synthetic.main.fragment_logined_wishlist.view.*

class WishlistFragment: Fragment() {
    private lateinit var adapter: ListAdapter
    private val viewModel by lazy{ ViewModelProvider(this).get(ListViewModel::class.java)}
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val db = firebaseDatabase.getReference("lodging-items")
    var auth: FirebaseAuth? = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_wishlist, container, false)

        if(auth?.currentUser != null) {
            view = LayoutInflater.from(activity)
                .inflate(R.layout.fragment_logined_wishlist, container, false)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(auth?.currentUser != null){

            val recyclerView:RecyclerView = view.findViewById(R.id.wish_recycle)
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
            observerData()

            wishlist_explain.text = null
            finding.text = null
        }
    }

    fun observerData(){
        viewModel.fetchData().observe( viewLifecycleOwner, Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}