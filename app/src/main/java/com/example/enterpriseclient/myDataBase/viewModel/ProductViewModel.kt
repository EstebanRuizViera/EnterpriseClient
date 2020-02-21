package com.example.enterpriseclient.myDataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.repository.ProductRepository

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

    fun getAllProduct():List<Product>? {

        return repository.getAllProduct()
    }

    fun getCountProduct(id: Int):Int? {

        return repository.getCountProduct()
    }
}