package com.example.miappgps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

data class TrackingUiState(
    val isRecording: Boolean = false,
    val currentTripId: Long? = null
)

class TrackingViewModel(application: Application) : AndroidViewModel(application) {


}