package com.example.firstproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.example.firstproject.R
import com.example.firstproject.activities.fragments.CartFragment
import com.example.firstproject.activities.fragments.CategoryFragment
import com.example.firstproject.databinding.ActivityDrawNavBinding

class DrawNavActivity : AppCompatActivity() {
    lateinit var headerView: View
    lateinit var ivPhoto: ImageView
    lateinit var tvUsername: TextView

    lateinit var binding: ActivityDrawNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)



        headerView = binding.navView.getHeaderView(0)

        ivPhoto = headerView.findViewById(R.id.iv_photo)
        tvUsername = headerView.findViewById(R.id.tv_username)

        val pref = getSharedPreferences("User", MODE_PRIVATE)
        val userName=pref.getString("name","").toString()
        Log.d("name","$userName")

       // tvUsername.setText(userName)
        tvUsername.text=userName
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
            //todo  order and profile
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
}