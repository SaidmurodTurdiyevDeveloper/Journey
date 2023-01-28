package com.journey.passenger_presenter

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
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
import com.journey.common_utils.broadcast.BluetoothReceiver
import com.journey.common_utils.navigator.Navigator
import com.journey.passenger_presenter.databinding.ActivityPassengerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassengerActivity : AppCompatActivity() {
    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, PassengerActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var _binding: ActivityPassengerBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var receiver: BluetoothReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPassengerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        registerReceiver()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_screen,
                R.id.service_screen,
                R.id.journey_screen,
                R.id.setting_screen
            )
        )


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_screen -> {
                    changeBottomNav(true)
                }

                R.id.service_screen -> {
                    changeBottomNav(true)
                }

                R.id.journey_screen -> {
                    changeBottomNav(true)
                }

                R.id.setting_screen -> {
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

object GoToPassengerActivity : Navigator {
    override fun navigate(activity: Activity) {
        PassengerActivity.launchActivity(activity)
    }
}