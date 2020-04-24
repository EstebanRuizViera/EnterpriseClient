package com.example.padwordbooking


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import com.example.padwordbooking.R.layout
import com.example.padwordbooking.cart.ShoppingCart
import com.example.padwordbooking.fragment.settings.SharePreferenceDarkMode
import com.example.padwordbooking.requestServer.RequestProduct
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

            RequestProduct.selectAllProducts(this, recyclerViewHome)
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

        cartSize.text=ShoppingCart.getProducts().size.toString()

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
        cartSize.text=ShoppingCart.getProducts().size.toString()
        super.onRestart()
    }
}
