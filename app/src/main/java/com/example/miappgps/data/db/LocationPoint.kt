package com.example.miappgps.data.db

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "location_points",
    primaryKeys = ["tripId","timestamp"],
    foreignKeys = [
        ForeignKey(
            entity = Trip::class, // Clave única
            parentColumns = ["id"],
            childColumns = ["tripId"],
            onDelete = ForeignKey.CASCADE // Si borras un Trip, se borran sus puntos
        )
    ]
)
data class LocationPoint(
    val tripId: Long,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long
)