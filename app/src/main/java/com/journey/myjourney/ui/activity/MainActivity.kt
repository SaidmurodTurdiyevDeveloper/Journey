package com.journey.myjourney.ui.activity

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.journey.myjourney.broadcast.BluetoothReceiver
import com.journey.myjourney.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var receiver: BluetoothReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        registerReceiver()
        val navHostFragment = supportFragmentManager.findFragmentById(com.journey.myjourney.R.id.fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                com.journey.myjourney.R.id.home_screen,
                com.journey.myjourney.R.id.service_screen,
                com.journey.myjourney.R.id.journey_screen,
                com.journey.myjourney.R.id.setting_screen
            )
        )


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.journey.myjourney.R.id.home_screen -> {
                    changeBottomNav(true)
                }
                com.journey.myjourney.R.id.service_screen -> {
                    changeBottomNav(true)
                }
                com.journey.myjourney.R.id.journey_screen -> {
                    changeBottomNav(true)
                }
                com.journey.myjourney.R.id.setting_screen -> {
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

    private fun registerReceiver() {
        receiver = BluetoothReceiver(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND)
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        registerReceiver(receiver, intentFilter)
    }

    fun getReceiver(): BluetoothReceiver? {
        return receiver
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        unregisterReceiver(receiver)
    }
}