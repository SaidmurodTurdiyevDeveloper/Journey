package com.example.myjourney.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.myjourney.broadcast.BluetoothReceiver
import com.example.myjourney.other.type.emptyBlock
import com.example.myjourney.other.type.sendOneParametreBlock
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.Closeable

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/4/2023.
 */
class BluetoothController(private var context: Context, private var adapter: BluetoothAdapter, private var receiver: BluetoothReceiver) : Closeable {
    private var onBluetoothOnListener: emptyBlock? = null
    private var onBluetoothOffListener: emptyBlock? = null
    private var onDeviceDiscoveringListener: emptyBlock? = null
    private var showMessageListener: sendOneParametreBlock<String>? = null
    private var boundingDevice: BluetoothDevice? = null

    fun setOnDiscoveringListener(block: emptyBlock): BluetoothController {
        onDeviceDiscoveringListener = block
        return this
    }

    fun setOnBluetoothOffListener(block: emptyBlock): BluetoothController {
        onBluetoothOffListener = block
        return this
    }


    fun setOnBluetoothOnListener(block: emptyBlock): BluetoothController {
        onBluetoothOnListener = block
        return this
    }

    fun setShowMessageListener(block: sendOneParametreBlock<String>): BluetoothController {
        showMessageListener = block
        return this
    }

    override fun close() {
        endDiscovery()
    }

    fun isBluetoothEnable(): Boolean = adapter.isEnabled

    fun startDiscovery() {
        Dexter
            .withContext(context)
            .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                        if (adapter.isDiscovering) {
                            adapter.cancelDiscovery()
                        }
                        if (adapter.startDiscovery()) {
                            onDeviceDiscoveringListener?.invoke()
                        }
                    }

                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    showMessageListener?.invoke("Permission Denied")
                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                    AlertDialog.Builder(context).setTitle("Bluetooth")
                        .setMessage("Access coarse location permission")
                        .setNegativeButton("dismiss") { dialog, _ ->
                            dialog.dismiss()
                            token?.cancelPermissionRequest()
                        }.setPositiveButton("allow") { dialog, _ ->
                            dialog.dismiss()
                            token?.continuePermissionRequest()
                        }.show()
                }
            }).check()
    }

    fun pair(device: BluetoothDevice): Boolean {
        endDiscovery()
        val outCome = if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            device.createBond()
        } else
            device.createBond()
        if (outCome) boundingDevice = device
        return outCome
    }

    fun isAlreadyPaired(device: BluetoothDevice): Boolean {
        return if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            adapter.bondedDevices
        } else {
            adapter.bondedDevices
        }
            .contains(device)
    }

    fun isDiscovering(): Boolean = if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
        adapter.isDiscovering
    } else
        adapter.isDiscovering

    fun getPairingDeviseStatus(): Int? {
        if (boundingDevice == null)
            return null
        val state = if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            boundingDevice?.bondState
        } else
            boundingDevice?.bondState
        if (state != BluetoothDevice.BOND_BONDING)
            boundingDevice = null
        return state
    }

    fun endDiscovery() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            if (adapter.isDiscovering) {
                adapter.cancelDiscovery()
            }
        } else
            if (adapter.isDiscovering) {
                adapter.cancelDiscovery()
            }
    }

    fun turnOnBluetooth() {
        onBluetoothOnListener?.invoke()
        if (!adapter.isEnabled) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                adapter.enable()
            }
        }
    }

    fun turnOffBluetooth() {
        onBluetoothOffListener?.invoke()
        if (adapter.isEnabled)
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                adapter.disable()
            }
    }
}