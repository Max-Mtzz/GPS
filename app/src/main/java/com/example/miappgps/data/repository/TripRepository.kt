package com.example.miappgps.data.repository

import android.content.Context
import com.example.miappgps.data.db.AppDatabase
import com.example.miappgps.data.db.LocationPoint
import com.example.miappgps.data.db.Trip

class TripRepository(context: Context) {

    private val tripDao = AppDatabase.getDatabase(context).tripDao()
    // Flujos de datos (para que la UI observe)
    fun getAllPoints() = tripDao.getAllLocationPoints()
    fun getAllCompletedTrips() = tripDao.getAllCompletedTrips()
    // Acciones (suspend fun)

    suspend fun startNewTrip(): Long {
        return tripDao.startNewTrip(Trip())
    }
    suspend fun saveLocationPoint(point: LocationPoint) {
        tripDao.insertLocationPoint(point)
    }
    suspend fun stopTripAndAddPhoto(tripId: Long, photoUri: String) {
        val trip = tripDao.getTripById(tripId)
        trip?.let {
            it.endTime = System.currentTimeMillis()
            it.photoUri = photoUri
            tripDao.updateTrip(it)
        }
    }
}