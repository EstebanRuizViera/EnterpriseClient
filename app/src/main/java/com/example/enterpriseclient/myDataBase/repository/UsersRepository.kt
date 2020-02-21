package com.example.enterpriseclient.myDataBase.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.enterpriseclient.myDataBase.dao.UserDao
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase
import com.example.enterpriseclient.myDataBase.model.User

class UsersRepository(application: Application) {
    private val userDao: UserDao? = ReservationDatabase.getInstance(application)?.userDao()

    fun insertUser(user: User) {
        if (userDao != null)
            InsertUserAsyncTask(userDao).execute(user)
    }

    fun getUser(id:Int):List<User>? {

        if (userDao != null)
            return GetUserAsyncTask(userDao).execute(id).get()
        return null
    }

    fun getUserId(id:Int):String {

        if (userDao != null)
            return GetUserIdAsyncTask(userDao).execute(id).get()
        return ""
    }

    fun getUserIdLocal(id:Int):Int {

        if (userDao != null)
            return GetUserIdLocalAsyncTask(userDao).execute(id).get()
        return 0
    }

    fun getUserToken(id:Int):String {

        if (userDao != null)
            return GetUserTokenAsyncTask(userDao).execute(id).get()
        return ""
    }


    fun updateUserToken(user: User) {
        if (userDao != null)
            UpdateUserTokenAsyncTask(userDao).execute(user)
    }

    fun updateUser(user: User) {
        if (userDao != null)
            UpdateUserAsyncTask(userDao).execute(user)
    }


    private class InsertUserAsyncTask(private val userDao: UserDao) :
        AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg users: User?): Void? {
            for (user in users) {
                if (user?.id_remoto != null && user?.token != null) {
                    userDao.insert(user)
                }

            }
            return null
        }
    }

    private class GetUserTokenAsyncTask(private val userDao: UserDao) :
        AsyncTask<Int, Void, String>() {
        override fun doInBackground(vararg ids: Int?): String {
            for (id in ids) {
                if (id != null) {
                    var user = userDao.getToken(id)
                    if (user != null) {
                        return user
                    }
                }
            }
            return ""
        }
    }

    private class GetUserAsyncTask(private val userDao: UserDao) :
        AsyncTask<Int, Void, List<User>>() {
        override fun doInBackground(vararg ids: Int?): List<User>? {
            for (id in ids) {
                if (id != null){
                    var user=userDao.getUser(id)
                    if(user !=null){
                        return user
                    }
                }
            }
            return null
        }
    }

    private class GetUserIdAsyncTask(private val userDao: UserDao) :
        AsyncTask<Int, Void, String>() {
        override fun doInBackground(vararg ids: Int?): String {
            for (id in ids) {
                if (id != null){
                    var user=userDao.getUserId(id)
                    if(user !=null){
                        return user
                    }
                }
            }
            return ""
        }
    }

    private class GetUserIdLocalAsyncTask(private val userDao: UserDao) :
        AsyncTask<Int, Void, Int>() {
        override fun doInBackground(vararg ids: Int?): Int {
            for (id in ids) {
                if (id != null){
                    var user=userDao.getUserIdLocal(id)
                    if(user !=null){
                        return user
                    }
                }
            }
            return 0
        }
    }

    private class UpdateUserTokenAsyncTask(private val userDao: UserDao) :
        AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg users: User?): Void? {
            for (user in users) {
                if (user?.id_remoto != null && user?.token != null)
                    userDao.updateToken(user!!.id_remoto,user!!.token)
            }
            return null
        }
    }

    private class UpdateUserAsyncTask(private val userDao: UserDao) :
        AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg users: User?): Void? {
            for (user in users) {
                if(user != null)
                    userDao.update(user)
            }
            return null
        }
    }
}