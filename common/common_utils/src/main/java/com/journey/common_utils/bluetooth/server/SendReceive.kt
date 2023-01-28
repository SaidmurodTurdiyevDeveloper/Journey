package com.journey.common_utils.bluetooth.server

import android.bluetooth.BluetoothSocket
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/12/2022.
 */
class SendReceive(private var socket: BluetoothSocket?) : Thread() {
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null

    init {
        try {
            inputStream = socket?.inputStream
            outputStream = socket?.outputStream
        } catch (e: Exception) {

        }
    }

    override fun start() {

    }

    override fun run() {

    }

}