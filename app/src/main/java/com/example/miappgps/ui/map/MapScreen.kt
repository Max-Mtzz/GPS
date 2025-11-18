package com.ejemplo.miappgps.ui.map

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miappgps.viewmodel.MapViewModel

import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

@Composable
fun MapScreen(
    viewModel: MapViewModel = viewModel()
) {
    val allPoints by viewModel.allTripPoints.collectAsState()

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            Configuration.getInstance().load(
                context,
                context.getSharedPreferences("osmdroid", 0)
            )

            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(17.0)

                allPoints.lastOrNull()?.let { last ->
                    controller.setCenter(GeoPoint(last.latitude, last.longitude))
                }
            }
        },
        update = { mapView ->
            mapView.overlays.clear()

            if (allPoints.isNotEmpty()) {
                val geoPoints = allPoints.map { GeoPoint(it.latitude, it.longitude) }
                val line = Polyline().apply {
                    setPoints(geoPoints)
                    outlinePaint.color = Color.BLUE
                    outlinePaint.strokeWidth = 8f
                }
                mapView.overlays.add(line)
                val startMarker = Marker(mapView).apply {
                    position = geoPoints.first()
                    title = "Inicio"
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                }
                mapView.overlays.add(startMarker)
                val endMarker = Marker(mapView).apply {
                    position = geoPoints.last()
                    title = "Fin"
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                }
                mapView.overlays.add(endMarker)


                mapView.controller.setCenter(geoPoints.last())
            }


            mapView.invalidate()
        }
    )
}