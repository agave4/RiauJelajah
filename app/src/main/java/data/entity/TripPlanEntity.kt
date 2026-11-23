package com.example.riaujelajah.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip_plans")
data class TripPlanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val destinationName: String,
    val city: String,
    val visitDate: String,
    val notes: String
)