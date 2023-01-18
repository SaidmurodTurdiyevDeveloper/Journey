package com.example.myjourney.data.model

import com.example.myjourney.bluetooth.server.SendRecive


/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/12/2022.
 */
sealed class BluetoothConnectingState {
    class MESSAGE(var connectingType: Int, var message: String? = null) : BluetoothConnectingState()
    class DATA(var connectingType: Int, var data: SendRecive) : BluetoothConnectingState()
}