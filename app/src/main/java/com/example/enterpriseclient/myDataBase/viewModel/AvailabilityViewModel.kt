package com.example.enterpriseclient.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.enterpriseclient.myDataBase.model.Availability
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.repository.AvailabilityRepository
import com.example.enterpriseclient.myDataBase.repository.UsersRepository

class AvailabilityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AvailabilityRepository(application)

    fun saveAvailability(availability: Availability) {
        repository.insertAvailability(availability)
    }

    fun updateAvailability(availability: Availability) {
        repository.updateAvailability(availability)
    }

    fun getAvailability(id: Int):List<Availability>? {

        return repository.getAvailability(id)
    }

}