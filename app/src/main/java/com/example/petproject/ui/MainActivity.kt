package com.example.petproject.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.petproject.R
import com.example.petproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation(this)
    }

    private fun setupNavigation(context: Context) {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth: Float = displayMetrics.widthPixels / displayMetrics.density

        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController

        if (dpWidth < 600) {
            binding.bottomNavigation?.setupWithNavController(navController)
        } else {
            binding.navigationRail?.setupWithNavController(navController)
        }

        // Setup top app bar
        val topAppBar = binding.topAppBar
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        topAppBar.setupWithNavController(navController, appBarConfiguration)
        topAppBar.setOnMenuItemClickListener { menuItem ->
            // settings nav
            NavigationUI.onNavDestinationSelected(menuItem, navController)

            // user change
            if (menuItem.itemId == R.id.user_mi) {
                Toast.makeText(this@MainActivity, "user changed", Toast.LENGTH_SHORT).show()
            }

            true
        }
    }
}