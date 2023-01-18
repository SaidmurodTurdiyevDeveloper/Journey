package com.example.myjourney.bluetooth.server

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.myjourney.other.type.sendOneParametreBlock
import java.io.IOException

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/12/2022.
 */
class ConnectThread(private var bluetoothAdapter: BluetoothAdapter, private var context: Context, private var mmSocket: BluetoothSocket) : Thread() {

    private var listener: sendOneParametreBlock<BluetoothSocket>? = null
    private var errorListener: sendOneParametreBlock<String>? = null

    fun setListener(block: sendOneParametreBlock<BluetoothSocket>) {
        listener = block
    }

    fun setErrorListener(block: sendOneParametreBlock<String>) {
        errorListener = block
    }

    override fun run() {
        // Cancel discovery because it otherwise slows down the connection.
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            bluetoothAdapter.cancelDiscovery()
        } else
            bluetoothAdapter.cancelDiscovery()

        mmSocket.let { socket ->
            try {
                socket.connect()
                listener?.invoke(socket)
            } catch (e: IOException) {
                errorListener?.invoke(e.message.toString())
            }
        }
    }

    // Closes the client socket and causes the thread to finish.
    fun cancel() {
        try {
            mmSocket.close()
        } catch (e: IOException) {
            errorListener?.invoke(e.message.toString())
        }
    }
}