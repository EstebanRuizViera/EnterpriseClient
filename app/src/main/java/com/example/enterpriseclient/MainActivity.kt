package com.example.enterpriseclient

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enterpriseclient.R.layout
import com.example.enterpriseclient.adapter.ProductAdapter
import com.example.enterpriseclient.bottomNavigationView.settings.SharePreferenceDarkMode
import com.example.enterpriseclient.myDataBase.model.Product
import com.example.enterpriseclient.myDataBase.viewModel.ProductViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_drawer.*


class MainActivity : BaseActivity() {

    private lateinit var productViewModel: ProductViewModel
    private var mToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        SharePreferenceDarkMode.checkDarkMode(this)

        super.onCreate(savedInstanceState)
        setContentView(layout.activity_drawer)
        setSupportActionBar(toolbar)

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

        val layoutManagerProducts = GridLayoutManager(this, 1)
        recyclerViewHome.setLayoutManager(layoutManagerProducts)

        //4º) Asigno al RecyclerView el adaptador que relaciona a cada item con su objeto a mostrar.
        val productAdapter =
            ProductAdapter(
                this,
                productsList
            )
        recyclerViewHome.setAdapter(productAdapter)
    }

    fun selectItemDrawer(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.menuhome ->{
//                val intent = Intent(this, )
//                startActivity(intent)
            }
            R.id.menulogin -> {}
//                IniciarSesion::class.java
            R.id.menuregister -> {}
//                Registrarse::class.java
            R.id.menulogout -> {}
//                CerrarSesion::class.java
            else -> {}
//                 Series::class.java
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cart_menu ->{
                val intent = Intent(this,CartListActivity::class.java)
                startActivity(intent)
            }
        }
        return if (mToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
