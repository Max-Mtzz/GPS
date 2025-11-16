package com.example.miappgps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.miappgps.data.Location.LocationClient
import com.example.miappgps.data.repository.TripRepository
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TrackingUiState(
    val isRecording: Boolean = false,
    val currentTripId: Long? = null
)

class TrackingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TripRepository(application)
    private val locationClient = LocationClient(
        application,
        LocationServices.getFusedLocationProviderClient(application)
    )

    private val _uiState = MutableStateFlow(TrackingUiState())
    val uiState = _uiState.asStateFlow()

    private var locationJob: Job? = null




}