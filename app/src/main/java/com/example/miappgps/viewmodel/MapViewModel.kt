package com.example.miappgps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.miappgps.data.db.LocationPoint
import com.example.miappgps.data.repository.TripRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MapViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TripRepository(application)

    val allTripPoints: StateFlow<List<LocationPoint>> =
        repository.getAllPoints()
            .stateIn(
                viewModelScope,
                SharingStarted.Companion.WhileSubscribed(5000),
                emptyList()
            )

}