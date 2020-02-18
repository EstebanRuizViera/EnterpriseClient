package com.example.enterpriseclient.myDataBase.repository

import android.app.Application
import android.os.AsyncTask
import com.example.enterpriseclient.myDataBase.dao.DistributionDao
import com.example.enterpriseclient.myDataBase.dao.ProductDao
import com.example.enterpriseclient.myDataBase.dao.UserDao
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase
import com.example.enterpriseclient.myDataBase.model.Distribution
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.model.User

class DistributionRepository(application: Application) {
    private val distributionDao: DistributionDao? =
        ReservationDatabase.getInstance(application)?.distributionDao()

    fun insertDistribution(distribution: Distribution) {
        if (distributionDao != null)
            InsertDistributionAsyncTask(distributionDao).execute(distribution)
    }

    fun getDistribution(id: Int): List<Distribution>? {

        if (distributionDao != null)
            return GetDistributionAsyncTask(distributionDao).execute(id).get()
        return null
    }

    fun getAllDistribution(): List<Distribution>? {

        if (distributionDao != null)
            return GetAllDistributionAsyncTask(distributionDao).execute().get()
        return null
    }


    fun updateDistribution(distribution: Distribution) {
        if (distributionDao != null)
            UpdateDistributionAsyncTask(distributionDao).execute(distribution)
    }


    private class InsertDistributionAsyncTask(private val distributionDao: DistributionDao) :
        AsyncTask<Distribution, Void, Void>() {
        override fun doInBackground(vararg distributions: Distribution?): Void? {
            for (distribution in distributions) {
                distributionDao.insertDistribution(distribution!!)
            }
            return null
        }
    }

    private class GetDistributionAsyncTask(private val distributionDao: DistributionDao) :
        AsyncTask<Int, Void, List<Distribution>>() {
        override fun doInBackground(vararg ids: Int?): List<Distribution>? {
            for (id in ids) {
                if (id != null) {
                    var distribution = distributionDao.getDistribution(id)
                    if (distribution != null) {
                        return distribution
                    }
                }
            }
            return null
        }
    }

    private class GetAllDistributionAsyncTask(private val distributionDao: DistributionDao) :
        AsyncTask<Void, Void, List<Distribution>>() {
        override fun doInBackground(vararg ids: Void?): List<Distribution>? {
            var distribution = distributionDao.getAllDistributions()
            if (distribution != null) {
                return distribution
            }
            return null
        }
    }

    private class UpdateDistributionAsyncTask(private val distributionDao: DistributionDao) :
        AsyncTask<Distribution, Void, Void>() {
        override fun doInBackground(vararg distributions: Distribution?): Void? {
            for (distribution in distributions) {
                if (distribution != null)
                    distributionDao.updateDistribution(distribution)
            }
            return null
        }
    }
}