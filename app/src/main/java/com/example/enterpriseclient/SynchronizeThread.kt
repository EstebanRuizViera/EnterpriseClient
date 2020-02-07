package com.example.enterpriseclient

import android.app.Activity
import android.widget.TextView
import android.widget.Toast
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import com.example.enterpriseclient.requestServer.RequestProduct


class SynchronizeThread: Runnable {

    private var activity: Activity
    private lateinit var productViewModel: ProductViewModel

    constructor(activity: Activity, productViewModel: ProductViewModel) {
        this.activity = activity
        this.productViewModel = productViewModel
    }

    override fun run() {

    }

    private fun sincronizedProduct(){
//        var productList = RequestProduct.selectAllProducts(activity.applicationContext,productViewModel)
//
//        for(product in productList){
//            Toast.makeText(activity, "id: "+product.id+" ,name: "+product.name+" ,descripcion: "+product.description+" ,image: "+product.image_url, Toast.LENGTH_SHORT).show()
//        }
    }
}