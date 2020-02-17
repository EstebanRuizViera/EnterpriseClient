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
import com.example.enterpriseclient.model.Product
import com.example.enterpriseclient.R
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.requestServer.RequestProduct

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    internal lateinit var sharedpref: SharePreferenceDarkMode

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharePreferenceDarkMode.checkDarkMode(this.activity as Activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)


        val root = inflater.inflate(R.layout.recyclerview_home, container, false)


        var productsList=arrayListOf<Product>()

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewHome)


        val layoutManagerProducts = GridLayoutManager(root.context, 1)
        recyclerView.setLayoutManager(layoutManagerProducts)

        RequestProduct.selectAllProducts(root.context, productsList, recyclerView)


//        var bookButton = root.findViewById<TextView>(R.id.bookButton)
//
//        bookButton.setOnClickListener {
//            val intent = Intent(activity, AvailabilityActivity::class.java)
//            startActivity(intent)
//        }

        return root
    }
}
