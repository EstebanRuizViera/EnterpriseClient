package com.example.appreservas.myDataBase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appreservas.myDataBase.dao.UserDao
import com.example.appreservas.myDataBase.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object {
        private const val DATABASE_NAME = "flight_ddbb"
        @Volatile
        private var INSTANCE: FlightDatabase? = null

        fun getInstance(context: Context): FlightDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    FlightDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}