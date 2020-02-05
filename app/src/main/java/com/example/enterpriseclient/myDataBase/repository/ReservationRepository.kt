package com.example.enterpriseclient.myDataBase.repository

import android.app.Application
import android.os.AsyncTask
import com.example.enterpriseclient.myDataBase.dao.ProductDao
import com.example.enterpriseclient.myDataBase.dao.ReservationDao
import com.example.enterpriseclient.myDataBase.dao.UserDao
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.model.Reservation
import com.example.enterpriseclient.myDataBase.model.User

class ReservationRepository(application: Application) {
    private val reservationDao:ReservationDao? = ReservationDatabase.getInstance(application)?.reservationDao()

    fun insertReservation(reservation: Reservation) {
        if (reservationDao != null)
            InsertReservationAsyncTask(reservationDao).execute(reservation)
    }

    fun getReservation(id:Int):List<Reservation>? {

        if (reservationDao != null)
            return GetReservationAsyncTask(reservationDao).execute(id).get()
        return null
    }

    fun updateReservation(reservation: Reservation) {
        if (reservationDao != null)
            UpdateReservationAsyncTask(reservationDao).execute(reservation)
    }


    private class InsertReservationAsyncTask(private val reservationDao:ReservationDao) :
        AsyncTask<Reservation, Void, Void>() {
        override fun doInBackground(vararg reservations: Reservation?): Void? {
            for (reservation in reservations) {
                reservationDao.insertReservation(reservation!!)
            }
            return null
        }
    }

    private class GetReservationAsyncTask(private val reservationDao:ReservationDao) :
        AsyncTask<Int, Void, List<Reservation>>() {
        override fun doInBackground(vararg ids: Int?): List<Reservation>? {
            for (id in ids) {
                if (id != null){
                    var reservation=reservationDao.getReservation(id)
                    if(reservation !=null){
                        return reservation
                    }
                }
            }
            return null
        }
    }

    private class UpdateReservationAsyncTask(private val reservationDao:ReservationDao) :
        AsyncTask<Reservation, Void, Void>() {
        override fun doInBackground(vararg reservations: Reservation?): Void? {
            for (reservation in reservations) {
                if(reservation != null)
                    reservationDao.updateReservation(reservation)
            }
            return null
        }
    }
}