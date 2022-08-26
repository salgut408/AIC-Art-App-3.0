package com.salgut.android.aicartapp.ui.artworkLocation

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.salgut.android.aicartapp.databinding.FragmentMapsBinding

class MapsFragment : Fragment() {
    lateinit var binding: FragmentMapsBinding
    val options = GoogleMapOptions()
    val args: MapsFragmentArgs by navArgs()
    var lat: Double = 0.0
    var long: Double = 0.0
    private val callback = OnMapReadyCallback { googleMap ->

        val location = LatLng(lat, long)
        googleMap.addMarker(MarkerOptions().position(location).title("Art location"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMapsBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val art = args.mapsArtworkSelected
        lat = art.latitude?.toDouble()!!
        long = art.longitude?.toDouble()!!
        val mapFragment = childFragmentManager.findFragmentById(this.id) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }
}