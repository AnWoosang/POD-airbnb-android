package com.pod.airbnb

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.SurfaceControl
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pod.airbnb.databinding.ActivityMainBinding
import com.pod.airbnb.navigation.*
import com.pod.airbnb.navigation.model.HostingDTO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var auth: FirebaseAuth
    lateinit var lodgeData: HostingDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, LoadingActivity::class.java))

        if(intent.getSerializableExtra("chatData") != null){
            lodgeData = intent.getSerializableExtra("lodgeData") as HostingDTO
            bottom_navigation.setOnNavigationItemSelectedListener(this)
            bottom_navigation.selectedItemId = R.id.action_wishlist

            val bundle = Bundle()
            bundle.putSerializable("lodgeData", lodgeData)
        } else{
            Log.d("fuck", "jwefioajfoiejfow")
            bottom_navigation.setOnNavigationItemSelectedListener(this)
            bottom_navigation.selectedItemId = R.id.action_search
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search ->{
                var searchFragment = SearchFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, searchFragment).commit()
                return true
            }

            R.id.action_wishlist ->{
                var wishlistFragment = WishlistFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, wishlistFragment).commit()
                return true
            }

            R.id.action_travel ->{
                var travelFragment = TravelFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, travelFragment).commit()
                return true
            }

            R.id.action_message ->{
                var messageFragment = MessageFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, messageFragment).commit()
                return true
            }

            R.id.action_profile ->{
                var profileFragment = ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content, profileFragment).commit()
                return true
            }

        }
        return false
    }
}
