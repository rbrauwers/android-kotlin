package com.rbrauwers.android_kotlin.maps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rbrauwers.android_kotlin.R
import com.rbrauwers.android_kotlin.utils.log
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by rodrigobrauwers on 20/03/18.
 */
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        log { "onMapReady" }
        val coordinates = LatLng(-29.459582, -51.964942)
        mMap = googleMap
        mMap.addMarker(MarkerOptions().position(coordinates).title("My Marker"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
    }
}