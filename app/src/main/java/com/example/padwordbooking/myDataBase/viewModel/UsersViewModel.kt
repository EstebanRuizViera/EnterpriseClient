package com.example.padwordbooking.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.padwordbooking.myDataBase.model.User
import com.example.padwordbooking.myDataBase.repository.UsersRepository

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UsersRepository(application)

    fun saveUser(user: User) {
        repository.insertUser(user)
    }

    fun updateToken(user: User) {
        repository.updateUserToken(user)
    }

    fun updateUser(user: User) {
        repository.updateUser(user)
    }

    fun getToken(id: Int):String {
        return repository.getUserToken(id)
    }

    fun getUser(id: Int): List<User>? {
        return repository.getUser(id)
    }

    fun getUserId(id: Int):String {
        return repository.getUserId(id)
    }

    fun getUserIdLocal(id: Int):Int {
        return repository.getUserIdLocal(id)
    }

}