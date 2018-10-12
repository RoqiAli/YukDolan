package com.roqiali.yukdolan.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.roqiali.yukdolan.R


class PlaceFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val candi_prambanan = LatLng(-7.75, 110.49417)
    private val candi_sambisari = LatLng(-7.7624319, 110.4470205)
    private val candi_ijo = LatLng(-7.7838266, 110.5096661)
    private val candi_kalasan = LatLng(-7.7672787, 110.4701618)
    private val candi_ratu_baka = LatLng(-7.7705363, 110.4872271)
    private val pantai_depok = LatLng(-8.014049, 110.292393)
    private val pantai_parangtritis = LatLng(-8.010057, 110.313009)
    private val pantai_baron = LatLng(-8.1294, 110.5475)
    private val pantai_insrayanti = LatLng(-8.1506, 110.6124)
    private val pantai_sundak = LatLng(-8.1471482, 110.6056589)
    private val kaliurang = LatLng(-7.5988019, 110.4263604)
    private val vredeburg = LatLng(-7.8002713, 110.3662998)
    private val keraton_jogja = LatLng(-7.8052845, 110.3642031)

    private lateinit var mcandi_prambanan: Marker
    private lateinit var mcandi_sambisari: Marker
    private lateinit var mcandi_ijo: Marker
    private lateinit var mcandi_kalasan: Marker
    private lateinit var mcandi_ratu_baka: Marker
    private lateinit var mpantai_depok: Marker
    private lateinit var mpantai_parangtritis: Marker
    private lateinit var mpantai_baron: Marker
    private lateinit var mpantai_insrayanti: Marker
    private lateinit var mpantai_sundak: Marker
    private lateinit var mkaliurang: Marker
    private lateinit var mvredeburg: Marker
    private lateinit var mkeraton_jogja: Marker


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_place, container, false)
        val googleMap = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        googleMap.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mcandi_prambanan = mMap.addMarker(MarkerOptions().position(candi_prambanan).title("Candi Prambanan"))
        mcandi_prambanan.tag = 0

        mcandi_sambisari = mMap.addMarker(MarkerOptions().position(candi_sambisari).title("Candi Sambisari"))
        mcandi_sambisari.tag = 0

        mcandi_ijo = mMap.addMarker(MarkerOptions().position(candi_ijo).title("Candi Ijo"))
        mcandi_ijo.tag = 0

        mcandi_kalasan = mMap.addMarker(MarkerOptions().position(candi_kalasan).title("Candi Kalasan"))
        mcandi_kalasan.tag = 0

        mcandi_ratu_baka = mMap.addMarker(MarkerOptions().position(candi_ratu_baka).title("Candi Ratu Baka"))
        mcandi_ratu_baka.tag = 0

        mpantai_depok = mMap.addMarker(MarkerOptions().position(pantai_depok).title("Pantai Depok"))
        mpantai_depok.tag = 0

        mpantai_parangtritis = mMap.addMarker(MarkerOptions().position(pantai_parangtritis).title("Pantai Parangtritis"))
        mpantai_parangtritis.tag = 0

        mpantai_baron = mMap.addMarker(MarkerOptions().position(pantai_baron).title("Pantai Baron"))
        mpantai_baron.tag = 0

        mpantai_insrayanti = mMap.addMarker(MarkerOptions().position(pantai_insrayanti).title("Pantai Indrayanti"))
        mpantai_insrayanti.tag = 0

        mpantai_sundak = mMap.addMarker(MarkerOptions().position(pantai_sundak).title("Pantai Sundak"))
        mpantai_sundak.tag = 0

        mkaliurang = mMap.addMarker(MarkerOptions().position(kaliurang).title("Kawasan Wisata Kaliurang"))
        mkaliurang.tag = 0

        mvredeburg = mMap.addMarker(MarkerOptions().position(vredeburg).title("Benteng Vredeburg"))
        mvredeburg.tag = 0

        mkeraton_jogja = mMap.addMarker(MarkerOptions().position(keraton_jogja).title("Keraton Jogja"))
        mkeraton_jogja.tag = 0

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(keraton_jogja, 10F))
    }


}
