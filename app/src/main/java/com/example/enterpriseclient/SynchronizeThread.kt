package com.example.enterpriseclient

import android.app.Activity
import android.widget.TextView
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.requestServer.RequestProduct


class SynchronizeThread: Runnable {

    private var activity: Activity

    constructor(activity: Activity) {
        this.activity = activity
    }

    override fun run() {

    }

    private fun sincronizedProduct(){
        //var productList = RequestProduct.selectAllProducts(activity.applicationContext)
    }
}