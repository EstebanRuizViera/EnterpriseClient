package com.example.enterpriseclient.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.enterpriseclient.myDataBase.model.Availability
import com.example.enterpriseclient.myDataBase.model.Distribution
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.model.User
import com.example.enterpriseclient.myDataBase.repository.AvailabilityRepository
import com.example.enterpriseclient.myDataBase.repository.DistributionRepository
import com.example.enterpriseclient.myDataBase.repository.ProductRepository
import com.example.enterpriseclient.myDataBase.repository.UsersRepository

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductRepository(application)

    fun saveProduct(product: Product) {
        repository.insertProduct(product)
    }

    fun updateProduct(product: Product) {
        repository.updateProduct(product)
    }

    fun getProduct(id: Int):List<Product>? {

        return repository.getProduct(id)
    }

    fun getCountProduct(id: Int):Int? {

        return repository.getCountProduct()
    }
}