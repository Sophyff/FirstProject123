package com.example.firstproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import com.example.firstproject.R
import com.example.firstproject.activities.fragments.CartFragment
import com.example.firstproject.activities.fragments.CategoryFragment
import com.example.firstproject.activities.fragments.OrdersFragment
import com.example.firstproject.activities.fragments.ProfileFragment
import com.example.firstproject.databinding.ActivityDrawNavBinding

class DrawNavActivity : AppCompatActivity() {
    lateinit var headerView: View
    lateinit var ivPhoto: ImageView
    lateinit var tvUsername: TextView
    lateinit var tvMobile:TextView

    lateinit var binding: ActivityDrawNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
     //   supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_search)


        headerView = binding.navView.getHeaderView(0)

        ivPhoto = headerView.findViewById(R.id.iv_photo)
        tvUsername = headerView.findViewById(R.id.tv_username)
        tvMobile = headerView.findViewById(R.id.tv_mobile)

        val pref = getSharedPreferences("User", MODE_PRIVATE)
        val userName=pref.getString("name","").toString()
        val mobile=pref.getString("mobile_no","").toString()
        Log.d("name","$userName")

       // tvUsername.setText(userName)
        tvUsername.text=userName
        tvMobile.text=mobile
        ivPhoto.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_profile))

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CategoryFragment()).commit()

        binding.navView.setNavigationItemSelectedListener {
            handleNavigationOperation(it)
            true
        }
    }

    private fun handleNavigationOperation(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.action_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CategoryFragment()).commit()
            }
            R.id.action_cart-> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CartFragment()).commit()
            }

            R.id.action_order-> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, OrdersFragment()).commit()
            }
            R.id.action_profile-> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment()).commit()
            }
            R.id.action_logout ->{
                val pref = getSharedPreferences("appsettings", MODE_PRIVATE)
                val editor = pref.edit()
                editor.clear()
                editor.commit()
                startActivity(Intent(baseContext, LoginActivity::class.java))
                finish()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchViewItem: MenuItem = menu.findItem(R.id.app_bar_search)
        val searchView: SearchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                val myIntent= Intent(baseContext, ProductDetailActivity::class.java)
                myIntent.putExtra("keyword",query)
                startActivity(myIntent)
                /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               // adapter.getFilter().filter(newText)
               return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


}