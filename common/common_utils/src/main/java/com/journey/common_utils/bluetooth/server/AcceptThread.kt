package com.journey.common_utils.bluetooth.server

import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import com.journey.common_utils.other.type.sendOneParametreBlock
import java.io.IOException

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/9/2022.
 */
class AcceptThread(private var mmServerSocket: BluetoothServerSocket) : Thread() {
    private var listener: sendOneParametreBlock<BluetoothSocket>? = null
    private var errorListener: sendOneParametreBlock<String>? = null

    fun setListener(block: sendOneParametreBlock<BluetoothSocket>) {
        listener = block
    }

    fun setErrorListener(block: sendOneParametreBlock<String>) {
        errorListener = block
    }

    override fun run() {
        // Keep listening until exception occurs or a socket is returned.
        var shouldLoop = true
        while (shouldLoop) {
            val socket: BluetoothSocket? = try {
                mmServerSocket.accept()
            } catch (e: IOException) {
                shouldLoop = false
                null
            }
            socket?.also {
                listener?.invoke(it)
                try {
                    mmServerSocket.close()
                } catch (e: IOException) {
                    errorListener?.invoke(e.message.toString())
                }
                shouldLoop = false
            }
        }
    }

    // Closes the connect socket and causes the thread to finish.
    fun cancel() {
        try {
            mmServerSocket.close()
        } catch (e: IOException) {
            errorListener?.invoke(e.message.toString())
        }
    }
}