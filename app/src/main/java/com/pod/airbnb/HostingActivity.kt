package com.pod.airbnb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.pod.airbnb.navigation.model.HostingDTO
import kotlinx.android.synthetic.main.activity_hosting.*

class HostingActivity : AppCompatActivity() {

    private lateinit var adapter: ListAdapter
    private val viewModel by lazy{ ViewModelProvider(this).get(ListViewModel::class.java)}

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hosting)

        auth = FirebaseAuth.getInstance()
        adapter = ListAdapter(this)

        val recyclerView: RecyclerView = findViewById(R.id.host_accomdation)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        observerData()

        if(auth?.currentUser == null){
            new_hosting.isClickable = false
        }else{
            new_hosting.setOnClickListener {
                startActivity(Intent(this, StartHostingActivity::class.java))
            }

            adapter.setOnItemClickListener(object: ListAdapter.OnItemClickListener{
                override fun onItemClick(v: View, data: HostingDTO, pos: Int) {
                    Intent(this@HostingActivity, HostingDetailActivity::class.java).apply {
                        putExtra("data", data)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.run { startActivity(this) }

                }

            })
        }

    }

    fun observerData(){
        viewModel.fetchData().observe(this, Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

}
