package com.example.enterpriseclient


import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enterpriseclient.R.layout
import com.example.enterpriseclient.cart.CartListActivity
import com.example.enterpriseclient.model.Product
import com.example.enterpriseclient.cart.ShoppingCart
import com.example.enterpriseclient.fragment.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.requestServer.RequestProduct
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_drawer.*


class DrawerActivity : BaseActivity() {

    private var mToggle: ActionBarDrawerToggle? = null

    private lateinit var cartSize:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)

        super.onCreate(savedInstanceState)
        setContentView(layout.activity_drawer)
        setSupportActionBar(toolbar)

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))

        swipeRefreshLayout.isRefreshing = true

        mToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer!!.addDrawerListener(mToggle!!)
        mToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setupDrawerContent(navigationView)

        setRecyclerView()
    }

    fun setRecyclerView(){

        var productsList = arrayListOf<Product>()

        val layoutManagerProducts = GridLayoutManager(this, 2)
        recyclerViewHome.setLayoutManager(layoutManagerProducts)

        RequestProduct.selectAllProducts(this,productsList,recyclerViewHome)

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

        var mMenu=menu!!.findItem(R.id.cart_menu) as MenuItem

        cartSize = mMenu.actionView.findViewById<TextView>(R.id.cart_size)
        var cart_frame = mMenu.actionView.findViewById<FrameLayout>(R.id.cart_frame)

        cartSize.text=ShoppingCart.getShoppingCartSize().toString()

        cart_frame.setOnClickListener{
            val intent = Intent(this, CartListActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (mToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onRestart() {
        cartSize.text=ShoppingCart.getShoppingCartSize().toString()
        super.onRestart()
    }
}
