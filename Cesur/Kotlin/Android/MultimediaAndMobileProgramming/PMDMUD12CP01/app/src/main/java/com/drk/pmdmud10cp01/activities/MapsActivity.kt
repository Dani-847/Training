package com.drk.pmdmud10cp01.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.drk.pmdmud10cp01.R
import com.drk.pmdmud10cp01.database.AppDatabase
import com.drk.pmdmud10cp01.databinding.ActivityMapsBinding
import com.drk.pmdmud10cp01.model.Pelicula
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var id: Int = 0
    private var lista: List<Pelicula> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id", 0)
        title = "Ubicaciones"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        lifecycleScope.launch {
            val dao = AppDatabase.getDatabase(this@MapsActivity).peliculaDao()
            lista = dao.getAllPeliculas().first()

            lista.forEach { p ->
                val posicion = LatLng(p.latitud, p.longitud)
                mMap.addMarker(
                    MarkerOptions()
                        .position(posicion)
                        .title(p.titulo)
                        .snippet("${p.director} (${p.anio})")
                )
            }

            lista.find { it.id == id }?.let { p ->
                val posicion = LatLng(p.latitud, p.longitud)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicion, 10f))
            }
        }

        mMap.setOnMarkerClickListener { marker ->
            Toast.makeText(this, marker.title, Toast.LENGTH_LONG).show()
            false
        }
    }
}
