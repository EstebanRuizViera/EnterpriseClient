package com.example.enterpriseclient.myDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.enterpriseclient.myDataBase.model.Availability
import com.example.enterpriseclient.myDataBase.model.User

@Dao
interface AvailabilityDao {
    @Insert
    fun insertAvailability(availability: Availability)

    @Update
    fun updateAvailability(vararg availability: Availability)

    @Delete
    fun deleteAvailability(vararg availability: Availability)

    @Query("SELECT * FROM " + Availability.TABLE_NAME + " WHERE id=:id")
    fun getAvailability(id: Int): List<Availability>

    @Query("SELECT * FROM "+ Availability.TABLE_NAME )
    fun getAllAvailability(): List<Availability>

}