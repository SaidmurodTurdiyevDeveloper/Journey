package com.example.myjourney.broadcast

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.example.myjourney.other.type.emptyBlock
import com.example.myjourney.other.type.returnParametreBlock
import com.example.myjourney.other.type.sendOneParametreBlock
import com.example.myjourney.ui.model.DataDevice

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/12/2022.
 */
class MyBluetoothBroadCast(private var myContext: Context) : BroadcastReceiver() {
    private var sendDataAction: sendOneParametreBlock<DataDevice>? = null
    private var getIdAction: returnParametreBlock<Int>? = null
    private var exception: emptyBlock? = null

    fun setAction(block: sendOneParametreBlock<DataDevice>) {
        sendDataAction = block
    }

    fun setException(block: emptyBlock) {
        exception = block
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
                            sendDataAction?.invoke(DataDevice(id, deviceName, device, deviceHardwareAddress))
                        } else {
                            exception?.invoke()
                        }
                    }
                    else -> {
                        exception?.invoke()
                    }
                }

            } catch (e: Exception) {
                exception?.invoke()
            }
        }
    }
}