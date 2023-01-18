package com.journey.myjourney.broadcast

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.journey.myjourney.other.type.emptyBlock
import com.journey.myjourney.other.type.returnParametreBlock
import com.journey.myjourney.other.type.sendOneParametreBlock
import com.journey.myjourney.ui.model.DataDevice

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/12/2022.
 */
class BluetoothReceiver(private var myContext: Context) : BroadcastReceiver() {
    private var addNewDeviceListener: sendOneParametreBlock<DataDevice>? = null
    private var statusChangeListener: emptyBlock? = null
    private var getIdAction: returnParametreBlock<Int>? = null
    private var exception: sendOneParametreBlock<String>? = null
    private var discoveryEndListener: emptyBlock? = null
    private var pairingEndListener: emptyBlock? = null

    fun setAddNewDeviceListener(block: sendOneParametreBlock<DataDevice>) {
        addNewDeviceListener = block
    }

    fun setExceptionListener(block: sendOneParametreBlock<String>) {
        exception = block
    }

    fun setDiscoveryEndListener(block: emptyBlock) {
        discoveryEndListener = block
    }

    fun setPairingEndListener(block: emptyBlock) {
        pairingEndListener = block
    }

    fun setStatusChangeListener(block: emptyBlock) {
        statusChangeListener = block
    }

    fun setGetIdAction(block: returnParametreBlock<Int>) {
        getIdAction = block
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { myIntent ->
            val action = myIntent.action
            try {
                when (action) {
                    BluetoothDevice.ACTION_FOUND -> {
                        val device: BluetoothDevice? =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                myIntent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE, BluetoothDevice::class.java)
                            } else {
                                myIntent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                            }
                        if (device != null) {
                            val deviceName = if (ActivityCompat.checkSelfPermission(context ?: myContext, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                                device.name
                            } else {
                                device.name
                            }
                            val deviceHardwareAddress = device.address // MAC address
                            val id = getIdAction?.invoke() ?: 0
                            addNewDeviceListener?.invoke(DataDevice(id, deviceName, device, deviceHardwareAddress))
                        } else {
                            exception?.invoke("Devise couldn`t find")
                        }
                    }
                    BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                        discoveryEndListener?.invoke()
                    }
                    BluetoothAdapter.ACTION_STATE_CHANGED -> {
                        statusChangeListener?.invoke()
                    }
                    BluetoothDevice.ACTION_BOND_STATE_CHANGED -> {
                        pairingEndListener?.invoke()
                    }
                    else -> {
                        exception?.invoke("Unexpected exception!")
                    }
                }

            } catch (e: Exception) {
                exception?.invoke(e.message.toString())
            }
        }
    }
}