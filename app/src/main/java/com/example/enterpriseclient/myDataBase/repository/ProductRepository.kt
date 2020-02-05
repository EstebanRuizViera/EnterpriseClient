package com.example.enterpriseclient.myDataBase.repository

import android.app.Application
import android.os.AsyncTask
import com.example.enterpriseclient.myDataBase.dao.ProductDao
import com.example.enterpriseclient.myDataBase.dao.UserDao
import com.example.enterpriseclient.myDataBase.database.ReservationDatabase
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.model.User

class ProductRepository(application: Application) {
    private val productDao: ProductDao? = ReservationDatabase.getInstance(application)?.productDao()

    fun insertProduct(product: Product) {
        if (productDao != null)
            InsertProductAsyncTask(productDao).execute(product)
    }

    fun getProduct(id:Int): List<Product>? {

        if (productDao != null)
            return GetProductAsyncTask(productDao).execute(id).get()
        return null
    }

    fun updateProduct(product: Product) {
        if (productDao != null)
            UpdateProductAsyncTask(productDao).execute(product)
    }


    private class InsertProductAsyncTask(private val productDao: ProductDao) :
        AsyncTask<Product, Void, Void>() {
        override fun doInBackground(vararg products: Product?): Void? {
            for (product in products) {
                productDao.insertProduct(product!!)
            }
            return null
        }
    }

    private class GetProductAsyncTask(private val productDao: ProductDao) :
        AsyncTask<Int, Void,  List<Product>>() {
        override fun doInBackground(vararg ids: Int?):  List<Product>? {
            for (id in ids) {
                if (id != null){
                    var product=productDao.getProduct(id)
                    if(product !=null){
                        return product
                    }
                }
            }
            return null
        }
    }

    private class UpdateProductAsyncTask(private val productDao: ProductDao) :
        AsyncTask<Product, Void, Void>() {
        override fun doInBackground(vararg products: Product?): Void? {
            for (product in products) {
                if(product != null)
                    productDao.updateProduct(product)
            }
            return null
        }
    }
}