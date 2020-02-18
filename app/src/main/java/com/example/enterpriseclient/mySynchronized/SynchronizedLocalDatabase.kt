package com.example.enterpriseclient.mySynchronized

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.enterpriseclient.model.AvailabilityPojo
import com.example.enterpriseclient.model.DistributionPojo
import com.example.enterpriseclient.model.ProductPojo
import com.example.enterpriseclient.myDataBase.model.Availability
import com.example.enterpriseclient.myDataBase.model.Distribution
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.viewModel.AvailabilityViewModel
import com.example.enterpriseclient.myDataBase.viewModel.DistributionViewModel
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import com.example.enterpriseclient.requestServer.RequestAvailability
import com.example.enterpriseclient.requestServer.RequestDistribution
import com.example.enterpriseclient.requestServer.RequestProduct

class SynchronizedLocalDatabase{

    private var productViewModel: ProductViewModel
    private var availabilityViewModel: AvailabilityViewModel
    private var distributionViewModel: DistributionViewModel

    private var activity:Activity

    private var listLocalProductPojos : ArrayList<ProductPojo> = arrayListOf()
    private var listRemoteProductPojos : ArrayList<ProductPojo> = arrayListOf()

    private var listLocalAvailabilityPojos : ArrayList<AvailabilityPojo> = arrayListOf()
    private var listRemoteAvailabilityPojos : ArrayList<AvailabilityPojo> = arrayListOf()

    private var listLocalDistributionPojos : ArrayList<DistributionPojo> = arrayListOf()
    private var listRemoteDistributionPojos : ArrayList<DistributionPojo> = arrayListOf()

    constructor(activity:Activity){

        this.activity = activity

        productViewModel = run {
            ViewModelProviders.of(activity as FragmentActivity).get(ProductViewModel::class.java)
        }
        availabilityViewModel = run {
            ViewModelProviders.of(activity as FragmentActivity).get(AvailabilityViewModel::class.java)
        }
        distributionViewModel = run {
            ViewModelProviders.of(activity as FragmentActivity).get(DistributionViewModel::class.java)
        }
    }

    fun syncronizedProduct(){
        getLocalProduct()
        getRemoteProducts()

        getLocalAvailability()
        getRemoteAvailability()

        getLocalDistribution()
        getRemoteDistribution()
    }

    fun saveProduct(){

        if(listRemoteProductPojos!=null && listLocalProductPojos!=null){
            if(listRemoteProductPojos.size>listLocalProductPojos.size){

                for(i in listLocalProductPojos.size..listRemoteProductPojos.size-1){
                    productViewModel.saveProduct(
                        Product(
                            listRemoteProductPojos.get(i).id,
                            listRemoteProductPojos.get(i).name,
                            listRemoteProductPojos.get(i).description,
                            listRemoteProductPojos.get(i).image_url,
                            listRemoteProductPojos.get(i).id_description
                        )
                    )
                    Log.println(Log.INFO, null, "product"+i )
                }
            }
        }else{
            Log.println(Log.INFO, null, "no product" )
        }
    }

    fun saveAvailability(){

        if(listRemoteAvailabilityPojos.size>listLocalAvailabilityPojos.size){

            for(i in listLocalAvailabilityPojos.size..listRemoteAvailabilityPojos.size-1){
                availabilityViewModel.saveAvailability(
                    Availability(
                        listRemoteAvailabilityPojos.get(i).id,
                        listRemoteAvailabilityPojos.get(i).dateAvailability,
                        listRemoteAvailabilityPojos.get(i).timeAvailability,
                        listRemoteAvailabilityPojos.get(i).price,
                        listRemoteAvailabilityPojos.get(i).quota,
                        listRemoteAvailabilityPojos.get(i).id_product
                    )
                )
                Log.println(Log.INFO, null, "availability"+i )
            }
        }

    }

    fun saveDistribution() {
        if(listRemoteDistributionPojos.size>listLocalDistributionPojos.size){

            Log.println(Log.INFO, null, ""+listLocalDistributionPojos.size )
            Log.println(Log.INFO, null, ""+listRemoteDistributionPojos.size )
            for(i in listLocalDistributionPojos.size..listRemoteDistributionPojos.size-1){
                distributionViewModel.saveDistribution(
                    Distribution(
                        listRemoteDistributionPojos.get(i).id,
                        listRemoteDistributionPojos.get(i).unit,
                        listRemoteDistributionPojos.get(i).duration,
                        listRemoteDistributionPojos.get(i).time_start,
                        listRemoteDistributionPojos.get(i).time_finish,
                        listRemoteDistributionPojos.get(i).block
                    )
                )
                Log.println(Log.INFO, null, "distribution" +i)
            }
        }
    }

    private fun getLocalProduct(){

        var list = productViewModel.getAllProduct()

        if(list!=null){
            for(product:Product in list.iterator()){
                listLocalProductPojos.add(
                    ProductPojo(
                        product.id,
                        product.name,
                        product.description,
                        product.image_url,
                        product.id_distribution
                    )
                )
            }

        }else{
            Toast.makeText(activity, "Error listLocalProducts is null", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getLocalAvailability(){

        var list = availabilityViewModel.getAllAvailability()

        if(list!=null){
            for(availability:Availability in list.iterator()){
                listLocalAvailabilityPojos.add(
                    AvailabilityPojo(
                        availability.id,
                        availability.dateAvailability,
                        availability.timeAvailability,
                        availability.price,
                        availability.quota,
                        availability.id_product
                    )
                )
            }

        }else{
            Toast.makeText(activity, "Error listLocalProducts is null", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getLocalDistribution(){

        var list = distributionViewModel.getAllDistribution()

        if(list!=null){
            for(distribution:Distribution in list.iterator()){
                listLocalDistributionPojos.add(
                    DistributionPojo(
                        distribution.id,
                        distribution.unit,
                        distribution.duration,
                        distribution.time_start,
                        distribution.time_finish,
                        distribution.block
                    )
                )
            }

        }else{
            Toast.makeText(activity, "Error listLocalProducts is null", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getRemoteProducts(){
        RequestProduct.selectAllProducts(activity,listRemoteProductPojos, this)
    }

    private fun getRemoteAvailability(){
        RequestAvailability.selectAvailabilityForProduct(activity,listRemoteAvailabilityPojos, this)
    }

    private fun getRemoteDistribution(){
        RequestDistribution.selectAllDistribution(activity,listRemoteDistributionPojos, this)
    }



}