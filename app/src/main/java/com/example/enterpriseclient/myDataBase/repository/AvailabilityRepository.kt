package com.example.enterpriseclient.myDataBase.repository

import android.app.Application
import android.os.AsyncTask
import com.example.enterpriseclient.myDataBase.dao.AvailabilityDao
import com.example.enterpriseclient.myDataBase.dao.ProductDao
import com.example.enterpriseclient.myDataBase.dao.UserDao
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase
import com.example.enterpriseclient.myDataBase.model.Availability
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.model.User

class AvailabilityRepository(application: Application) {
    private val availabilityDao: AvailabilityDao? = ReservationDatabase.getInstance(application)?.availabilityDao()

    fun insertAvailability(availability: Availability) {
        if (availabilityDao != null)
            InsertAvailabilityAsyncTask(availabilityDao).execute(availability)
    }

    fun getAvailability(id:Int):List<Availability>? {

        if (availabilityDao != null)
            return GetAvailabilityrAsyncTask(availabilityDao).execute(id).get()
        return null
    }

    fun updateAvailability(availability: Availability) {
        if (availabilityDao != null)
            UpdateAvailabilityAsyncTask(availabilityDao).execute(availability)
    }


    private class InsertAvailabilityAsyncTask(private val availabilityDao: AvailabilityDao) :
        AsyncTask<Availability, Void, Void>() {
        override fun doInBackground(vararg availabilitys: Availability?): Void? {
            for (availability in availabilitys) {
                availabilityDao.insertAvailability(availability!!)
            }
            return null
        }
    }

    private class GetAvailabilityrAsyncTask(private val availabilityDao: AvailabilityDao) :
        AsyncTask<Int, Void, List<Availability>>() {
        override fun doInBackground(vararg ids: Int?): List<Availability>? {
            for (id in ids) {
                if (id != null){
                    var availability=availabilityDao.getAvailability(id)
                    if(availability !=null){
                        return availability
                    }
                }
            }
            return null
        }
    }

    private class UpdateAvailabilityAsyncTask(private val availabilityDao: AvailabilityDao) :
        AsyncTask<Availability, Void, Void>() {
        override fun doInBackground(vararg availabilitys: Availability?): Void? {
            for (availability in availabilitys) {
                if(availability != null)
                    availabilityDao.updateAvailability(availability)
            }
            return null
        }
    }
}