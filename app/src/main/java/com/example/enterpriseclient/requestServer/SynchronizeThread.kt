package com.example.enterpriseclient.requestServer

import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import com.example.enterpriseclient.requestServer.RequestProduct
import kotlinx.coroutines.awaitAll


class SynchronizeThread: AsyncTask<String,String,String> {

    private var activity: Activity
    private var productViewModel: ProductViewModel
    private var totalRowServer:Int = 0
    private var totalRowClient:Int = 0


    constructor(activity: Activity, productViewModel: ProductViewModel) {
        this.activity = activity
        this.productViewModel = productViewModel
    }

    override fun onProgressUpdate(vararg values: String?) {
    }

    override fun onPreExecute() {


        suspend {
            RequestProduct.countProducts(activity.applicationContext, this)
        }
        Log.println(Log.INFO, null, "TOTAL: ")
    }

    override fun doInBackground(vararg params: String?): String {
        return ""
    }

    override fun onPostExecute(result: String?) {
    }

    private fun sincronizedProduct(){
//        var productList = RequestProduct.selectAllProducts(activity.applicationContext,productViewModel)
//
//        for(product in productList){
//            Toast.makeText(activity, "id: "+product.id+" ,name: "+product.name+" ,descripcion: "+product.description+" ,image: "+product.image_url, Toast.LENGTH_SHORT).show()
//        }
    }

}