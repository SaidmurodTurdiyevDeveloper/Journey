package com.example.myjourney.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.myjourney.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(com.example.myjourney.R.id.fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                com.example.myjourney.R.id.home_screen,
                com.example.myjourney.R.id.service_screen,
                com.example.myjourney.R.id.journey_screen,
                com.example.myjourney.R.id.setting_screen
            )
        )


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.example.myjourney.R.id.home_screen -> {
                    changeBottomNav(true)
                }
                com.example.myjourney.R.id.service_screen -> {
                    changeBottomNav(true)
                }
                com.example.myjourney.R.id.journey_screen -> {
                    changeBottomNav(true)
                }
                com.example.myjourney.R.id.setting_screen -> {
                    changeBottomNav(true)
                }
                else -> changeBottomNav(false)
            }
        }
        setupWithNavController(binding.bottomNav, navController)
    }

    private fun changeBottomNav(cond: Boolean) {
        binding.bottomNav.isVisible = cond
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}