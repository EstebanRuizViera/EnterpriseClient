package com.example.enterpriseclient.mySynchronized

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.enterpriseclient.App
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
import com.example.enterpriseclient.requestServer.RequestReport

class SynchronizedLocalDatabase {

    private var productViewModel: ProductViewModel
    private var availabilityViewModel: AvailabilityViewModel
    private var distributionViewModel: DistributionViewModel

    private var context: Context
    private var application: App

    private var listLocalProductPojos: ArrayList<ProductPojo> = arrayListOf()
    private var listRemoteProductPojos: ArrayList<ProductPojo> = arrayListOf()

    private var listLocalAvailabilityPojos: ArrayList<AvailabilityPojo> = arrayListOf()
    private var listRemoteAvailabilityPojos: ArrayList<AvailabilityPojo> = arrayListOf()

    private var listLocalDistributionPojos: ArrayList<DistributionPojo> = arrayListOf()
    private var listRemoteDistributionPojos: ArrayList<DistributionPojo> = arrayListOf()

    constructor(context: Context, application: App) {

        this.context = context
        this.application = application

        productViewModel = ProductViewModel(application)
        availabilityViewModel = AvailabilityViewModel(application)
        distributionViewModel = DistributionViewModel(application)

    }

    fun syncronizedProduct() {
        getLocalProduct()
        getRemoteProducts()

        //
    }

    fun saveProduct() {

        if (listRemoteProductPojos != null && listLocalProductPojos != null) {
            if (listRemoteProductPojos.size > listLocalProductPojos.size) {

                for (i in listLocalProductPojos.size..listRemoteProductPojos.size - 1) {
                    productViewModel.saveProduct(
                        Product(
                            listRemoteProductPojos.get(i).id,
                            listRemoteProductPojos.get(i).name,
                            listRemoteProductPojos.get(i).description,
                            listRemoteProductPojos.get(i).image_url,
                            listRemoteProductPojos.get(i).id_description
                        )
                    )
                    Log.println(Log.INFO, null, "product " + i)
                }
            }
        } else {
            Log.println(Log.INFO, null, "no product")
        }
        getLocalAvailability()
        getRemoteAvailability()
    }

    fun saveAvailability() {

        if (listRemoteAvailabilityPojos.size > listLocalAvailabilityPojos.size) {

            for (i in listLocalAvailabilityPojos.size..listRemoteAvailabilityPojos.size - 1) {
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
                Log.println(Log.INFO, null, "availability " + i)
            }
        }
        getLocalDistribution()
        getRemoteDistribution()
    }

    fun saveDistribution() {
        if (listRemoteDistributionPojos.size > listLocalDistributionPojos.size) {

            Log.println(Log.INFO, null, "" + listLocalDistributionPojos.size)
            Log.println(Log.INFO, null, "" + listRemoteDistributionPojos.size)
            for (i in listLocalDistributionPojos.size..listRemoteDistributionPojos.size - 1) {
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
                Log.println(Log.INFO, null, "distribution " + i)
            }
        }
        RequestReport.generateReportListProduct(context)
    }

    fun getLocalProduct() {

        var list = productViewModel.getAllProduct()

        if (list != null) {
            for (product: Product in list.iterator()) {
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

        } else {
            var toast =Toast.makeText(context, "Error listLocalProducts is null", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
            toast.show()
        }
    }

    fun getLocalAvailability() {

        var list = availabilityViewModel.getAllAvailability()

        if (list != null) {
            for (availability: Availability in list.iterator()) {
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

        } else {
            var toast = Toast.makeText(context, "Error listLocalProducts is null", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
            toast.show()
        }
    }

    fun getLocalDistribution() {

        var list = distributionViewModel.getAllDistribution()

        if (list != null) {
            for (distribution: Distribution in list.iterator()) {
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

        } else {
            var toast = Toast.makeText(context, "Error listLocalProducts is null", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER or Gravity.BOTTOM, 0, 1000)
            toast.show()
        }
    }

    fun getRemoteProducts() {
        RequestProduct.selectAllProducts(context, listRemoteProductPojos, this)
    }

    fun getRemoteAvailability() {
        RequestAvailability.selectAllAvailabilities(context, listRemoteAvailabilityPojos, this)
    }

    fun getRemoteDistribution() {
        RequestDistribution.selectAllDistribution(context, listRemoteDistributionPojos, this)
    }


}