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
import com.example.petproject.ui.bottom_sheet.BottomSheet
import com.example.petproject.utils.getWidth
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheet: BottomSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation(this)
    }

    private fun showBottomSheet() {
        bottomSheet = BottomSheet { user ->
            Toast.makeText(this@MainActivity, user.username, Toast.LENGTH_SHORT).show()
            hideBottomSheet()
        }
        bottomSheet.show(supportFragmentManager, BottomSheet.TAG)
    }

    private fun hideBottomSheet() {
        bottomSheet.dismiss()
    }

    private fun setupNavigation(context: Context) {
        val navHostFragment = supportFragmentManager.findFragmentById(binding.mainContent.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController

        if (getWidth(context) < 600) {
            binding.mainContent.bottomNavigation?.setupWithNavController(navController)
        } else {
            binding.mainContent.navigationRail?.setupWithNavController(navController)
        }

        // Setup top app bar
        val topAppBar = binding.mainContent.topAppBar
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        topAppBar.setupWithNavController(navController, appBarConfiguration)
        topAppBar.setOnMenuItemClickListener { menuItem ->
            // setup settings nav
            NavigationUI.onNavDestinationSelected(menuItem, navController)
            // user change
            if (menuItem.itemId == R.id.user_mi) {
                showBottomSheet()
            }
            true
        }
    }
}