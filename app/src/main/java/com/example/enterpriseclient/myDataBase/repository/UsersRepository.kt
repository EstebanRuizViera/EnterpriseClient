package com.example.appreservas.myDataBase.repository

import android.app.Application
import android.os.AsyncTask
import com.example.appreservas.myDataBase.dao.UserDao
import com.example.appreservas.myDataBase.database.FlightDatabase
import com.example.appreservas.myDataBase.model.User

class UsersRepository(application: Application) {
    private val userDao: UserDao? = FlightDatabase.getInstance(application)?.userDao()

    fun insert(user: User) {
        if (userDao != null)
            InsertAsyncTask(userDao).execute(user)
    }

    fun getUserId(id:Int):String {

        if (userDao != null)
            return IdUserAsyncTask(userDao).execute(id).get()
        return ""
    }

    fun getUserIdLocal(id:Int):Int {

        if (userDao != null)
            return IdUserLocalAsyncTask(userDao).execute(id).get()
        return 0
    }

    fun getToken(id:Int):String {

        if (userDao != null)
            return GetTokenAsyncTask(userDao).execute(id).get()
        return ""
    }


    fun updateToken(user: User) {
        if (userDao != null)
            UpdateTokenAsyncTask(userDao).execute(user)
    }

    fun updateUser(user: User) {
        if (userDao != null)
            UpdateUserAsyncTask(userDao).execute(user)
    }


    private class InsertAsyncTask(private val userDao: UserDao) :
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

    private class GetTokenAsyncTask(private val userDao: UserDao) :
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

    private class IdUserAsyncTask(private val userDao: UserDao) :
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

    private class IdUserLocalAsyncTask(private val userDao: UserDao) :
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

    private class UpdateTokenAsyncTask(private val userDao: UserDao) :
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