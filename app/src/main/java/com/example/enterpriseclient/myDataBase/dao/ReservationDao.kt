package com.example.enterpriseclient.myDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.model.Reservation
import com.example.enterpriseclient.myDataBase.model.User

@Dao
interface ReservationDao {
    @Insert
    fun insertReservation(reservation: Reservation)

    @Update
    fun updateReservation(vararg reservation: Reservation)

    @Delete
    fun deleteReservation(vararg reservation: Reservation)

    @Query("SELECT * FROM " + Reservation.TABLE_NAME + " WHERE id=:id")
    fun getReservation(id: Int): List<Reservation>

    @Query("SELECT * FROM "+ Reservation.TABLE_NAME )
    fun getAllReservations(): List<Reservation>

}