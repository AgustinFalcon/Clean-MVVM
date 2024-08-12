package com.example.challengeconexa.ui.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import android.Manifest
import android.widget.Toast
import com.example.challengeconexa.R
import com.example.challengeconexa.databinding.FragmentMapBinding
import com.example.challengeconexa.databinding.FragmentNewsBinding
import com.example.challengeconexa.ui.fragment.detail.DetailNewsFragmentArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private val args: MapFragmentArgs by navArgs()

    private lateinit var googleMap: GoogleMap
    private var userLocation: LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            setupMap()
        }


    }

    private fun setupMap() {

        val location = args.location
        val latitude = location.lat.toDoubleOrNull() ?: -34.6036613
        val longitude = location.lng.toDoubleOrNull() ?: -58.4227699

        userLocation = LatLng(latitude, longitude)

        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction()
            .replace(R.id.map_container, mapFragment)
            .commit()

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        userLocation?.let {
            googleMap.addMarker(MarkerOptions().position(it).title("User Location"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 15f))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupMap()
            } else {
                Toast.makeText(requireContext(), "Acepte los permisos!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun setUserLocation(latitude: Double, longitude: Double) {
        userLocation = LatLng(latitude, longitude)
        if (::googleMap.isInitialized) {
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(userLocation!!).title("User Location"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation!!, 15f))
        }
    }


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

}