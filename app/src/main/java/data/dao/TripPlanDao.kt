package com.example.riaujelajah.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.riaujelajah.data.entity.TripPlanEntity

@Dao
interface TripPlanDao {
    @Query("SELECT * FROM trip_plans ORDER BY id DESC")
    suspend fun getAll(): List<TripPlanEntity>

    @Insert
    suspend fun insert(plan: TripPlanEntity)

    @Delete
    suspend fun delete(plan: TripPlanEntity)
}