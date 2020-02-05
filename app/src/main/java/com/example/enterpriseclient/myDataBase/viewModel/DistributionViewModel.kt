package com.example.enterpriseclient.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.enterpriseclient.myDataBase.model.Availability
import com.example.enterpriseclient.myDataBase.model.Distribution
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.repository.AvailabilityRepository
import com.example.enterpriseclient.myDataBase.repository.DistributionRepository
import com.example.enterpriseclient.myDataBase.repository.UsersRepository

class DistributionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DistributionRepository(application)

    fun saveDistribution(distribution: Distribution) {
        repository.insertDistribution(distribution)
    }

    fun updateDistribution(distribution: Distribution) {
        repository.updateDistribution(distribution)
    }

    fun getDistribution(id: Int):List<Distribution>? {

        return repository.getDistribution(id)
    }

}