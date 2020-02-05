package com.example.enterpriseclient.myDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.enterpriseclient.myDataBase.model.Availability
import com.example.enterpriseclient.myDataBase.model.Distribution
import com.example.enterpriseclient.myDataBase.model.User

@Dao
interface DistributionDao {
    @Insert
    fun insertDistribution(distribution: Distribution)

    @Update
    fun updateDistribution(vararg distribution: Distribution)

    @Delete
    fun deleteDistribution(vararg distribution: Distribution)

    @Query("SELECT * FROM " + Distribution.TABLE_NAME + " WHERE id=:id")
    fun getDistribution(id: Int): List<Distribution>

    @Query("SELECT * FROM "+ Distribution.TABLE_NAME )
    fun getAllDistributions(): List<Distribution>

}