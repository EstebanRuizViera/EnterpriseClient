package com.example.enterpriseclient.myDataBase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.enterpriseclient.myDataBase.model.Distribution
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.model.User

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(product: Product)

    @Update
    fun updateProduct(vararg product: Product)

    @Delete
    fun deleteProduct(vararg product: Product)

    @Query("SELECT id_remoto FROM " + Product.TABLE_NAME + " WHERE id=:id")
    fun getProduct(id: Int): List<Product>

    @Query("SELECT * FROM "+ Product.TABLE_NAME )
    fun getAllProduct(): List<Product>

}