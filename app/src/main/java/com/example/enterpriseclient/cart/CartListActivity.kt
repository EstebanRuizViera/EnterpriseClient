package com.example.enterpriseclient.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enterpriseclient.R
import com.example.enterpriseclient.fragment.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.model.ProductProfilePojo
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import com.example.enterpriseclient.requestServer.RequestProduct
import kotlinx.android.synthetic.main.activity_cart_list.*

class CartListActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)
        setSupportActionBar(toolbar)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)


        setRecyclerView()
    }

    private fun setRecyclerView(){
        var productsList=arrayListOf<ProductProfilePojo>()

//        productsList = productViewModel.getAllProduct() as ArrayList<ProductProfilePojo>

        val layoutManagerProducts = GridLayoutManager(this, 1)
        recyclerViewCart.setLayoutManager(layoutManagerProducts)

        RequestProduct.selectAllProductsForCustomer(this, productsList, recyclerViewCart, "1")

//        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
//        val productAdapter =
//            CartAdapter(
//                this,
//                productsList
//            )
//        recyclerViewCart.setAdapter(productAdapter)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
