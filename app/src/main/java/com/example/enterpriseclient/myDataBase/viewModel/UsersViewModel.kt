package com.example.appreservas.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.appreservas.myDataBase.model.User
import com.example.appreservas.myDataBase.repository.UsersRepository

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UsersRepository(application)

    fun saveUser(user: User) {
        repository.insert(user)
    }

    fun updateToken(user: User) {
        repository.updateToken(user)
    }

    fun updateUser(user: User) {
        repository.updateUser(user)
    }


    fun getToken(id: Int):String {
        return repository.getToken(id)
    }

    fun getUserId(id: Int):String {
        return repository.getUserId(id)
    }

    fun getUserIdLocal(id: Int):Int {
        return repository.getUserIdLocal(id)
    }

}