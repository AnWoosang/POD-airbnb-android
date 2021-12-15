package com.pod.airbnb

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.FirebaseDatabase
import com.pod.airbnb.navigation.model.ChattingDTO
import com.pod.airbnb.navigation.model.HostingDTO
import kotlinx.android.synthetic.main.activity_hosting_detail.*
import org.jetbrains.anko.find

class HostingDetailActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var latLng : LatLng
    lateinit var datas : HostingDTO
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val db = firebaseDatabase.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hosting_detail)

        val geocoder = Geocoder(this)

        datas = intent.getSerializableExtra("data") as HostingDTO
//        if(datas.favorite!!){
//        wish_heart.setSelected(datas.favorite!!)
//    }
        lodge_name.text = datas.name
        lodge_contents.text = datas.description

        wish_heart.setOnClickListener {
            if(wish_heart.isSelected){
                wish_heart.setSelected(false)
                db.child("lodging-items").child("favorite").setValue(false)
            } else{
                wish_heart.setSelected(true)
                db.child("lodging-items").child("favorite").setValue(true)
            }
        }


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "위치 권한을 설정해주세요.", Toast.LENGTH_SHORT).show()
        } else{
            if(datas.streetKey != null){
                val cor = geocoder.getFromLocationName(datas.streetKey, 1)
                Log.d("TAG", "좌표 : ${cor[0].latitude}, ${cor[0].longitude}")
                latLng = LatLng(cor[0].latitude, cor[0].longitude)
                val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment!!.getMapAsync(this)
            } else{
                latLng = LatLng(datas.latitude!!, datas.longitude!!)
                val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment!!.getMapAsync(this)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    /** Called when the user clicks a marker.  */
    override fun onMarkerClick(marker: Marker): Boolean {

        // Retrieve the data from the marker.
        val clickCount = marker.tag as? Int

        // Check if a click count was set, then display the click count.
        clickCount?.let {
            val newClickCount = it + 1
            marker.tag = newClickCount
            Toast.makeText(
                this,
                "${marker.title} has been clicked $newClickCount times.",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(latLng)
//                .title(input.text.toString())
        // 타이틀
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }
}
