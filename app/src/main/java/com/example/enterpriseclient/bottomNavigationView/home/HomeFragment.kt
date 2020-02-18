package com.example.enterpriseclient.bottomNavigationView.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enterpriseclient.model.ProductPojo
import com.example.enterpriseclient.R
import com.example.enterpriseclient.adapter.ProductAdapter
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var productViewModel: ProductViewModel

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharePreferenceDarkMode.checkDarkMode(this.activity as Activity)

        productViewModel = run {
            ViewModelProviders.of(this).get(ProductViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_home, container, false)


        var productsList=arrayListOf<Product>()
        
        productsList = productViewModel.getAllProduct() as ArrayList<Product>

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewHome)


        val layoutManagerProducts = GridLayoutManager(root.context, 1)
        recyclerView.setLayoutManager(layoutManagerProducts)

        //RequestProduct.selectAllProducts(root.context, productsList, recyclerView)

        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
        val productAdapter =
            ProductAdapter(
                context!!,
                productsList
            )
        recyclerView.setAdapter(productAdapter)


        return root
    }
}
