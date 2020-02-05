package com.example.enterpriseclient.myDataBase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.enterpriseclient.myDataBase.dao.*
import com.example.enterpriseclient.myDataBase.model.*

@Database(entities = [User::class,Product::class,Availability::class,Distribution::class,Reservation::class], version = 1, exportSchema = false)
abstract class ReservationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun availabilityDao(): AvailabilityDao
    abstract fun distributionDao(): DistributionDao
    abstract fun reservationDao(): ReservationDao


    companion object {
        private const val DATABASE_NAME = "reservation"
        @Volatile
        private var INSTANCE: ReservationDatabase? = null

        fun getInstance(context: Context): ReservationDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ReservationDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}