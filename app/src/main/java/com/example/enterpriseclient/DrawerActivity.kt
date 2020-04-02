package com.example.enterpriseclient

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enterpriseclient.R.layout
import com.example.enterpriseclient.adapter.ProductAdapter
import com.example.enterpriseclient.cart.CartListActivity
import com.example.enterpriseclient.cart.ShoppingCart
import com.example.enterpriseclient.fragment.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.fragment.user.UserFragment
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.header.*


class DrawerActivity : BaseActivity() {

    private lateinit var productViewModel: ProductViewModel
    private var mToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)

        super.onCreate(savedInstanceState)
        setContentView(layout.activity_drawer)
        setSupportActionBar(toolbar)

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))

        swipeRefreshLayout.isRefreshing = true

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        mToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer!!.addDrawerListener(mToggle!!)
        mToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setupDrawerContent(navigationView)

        setRecyclerView()

    }

    fun setRecyclerView(){

        var productsList = productViewModel.getAllProduct() as ArrayList<Product>

        swipeRefreshLayout.isRefreshing = false
        swipeRefreshLayout.isEnabled = false

        val layoutManagerProducts = GridLayoutManager(this, 2)
        recyclerViewHome.setLayoutManager(layoutManagerProducts)

        val productAdapter =
            ProductAdapter(
                this,
                productsList
            )
        recyclerViewHome.setAdapter(productAdapter)
    }

    fun selectItemDrawer(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.menuProfile ->{
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.menuSettings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.menuBookings -> {
                val intent = Intent(this, MyBookingsActivity::class.java)
                startActivity(intent)
            }
        }
        menuItem.isChecked = true
        title = menuItem.title
        drawer!!.closeDrawers()
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { item ->
            selectItemDrawer(item)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_action_bar,menu)

//        var mMenu=menu!!.findItem(R.id.cart_menu) as MenuItem
//        var cartSize = mMenu.actionView.findViewById<TextView>(R.id.cart_size)
//        cartSize.text=ShoppingCart.getShoppingCartSize().toString()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cart_menu ->{
                val intent = Intent(this, CartListActivity::class.java)
                startActivity(intent)
            }
        }
        return if (mToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
