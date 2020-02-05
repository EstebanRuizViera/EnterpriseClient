package com.example.enterpriseclient.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.enterpriseclient.myDataBase.model.*
import com.example.enterpriseclient.myDataBase.repository.*

class ReservationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ReservationRepository(application)

    fun saveProduct(reservation: Reservation) {
        repository.insertReservation(reservation)
    }

    fun updateProduct(reservation: Reservation) {
        repository.updateReservation(reservation)
    }

    fun getProduct(id: Int):List<Reservation>? {

        return repository.getReservation(id)
    }

}