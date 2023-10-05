package com.ebrahimipooria.storeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ebrahimipooria.storeapp.databinding.ActivityProductsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvProductsBottomNavView.selectedItemId.and(R.id.homeFragment)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bnv_Products_BottomNavView)
        navView.setupWithNavController(navController)




    }
}