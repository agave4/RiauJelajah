package com.example.riaujelajah.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.riaujelajah.data.dao.TripPlanDao
import com.example.riaujelajah.data.entity.TripPlanEntity

@Database(
    entities = [TripPlanEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripPlanDao(): TripPlanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "riau_jelajah_db"
                ).build().also { INSTANCE = it }
            }
    }
}