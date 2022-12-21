package com.example.myjourney.ui.screen.setting

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myjourney.R
import com.example.myjourney.broadcast.MyBluetoothBroadCast
import com.example.myjourney.databinding.ScreenConnectionBluetoothBinding
import com.example.myjourney.other.Constants
import com.example.myjourney.other.extention.showToast
import com.example.myjourney.server.AcceptThread
import com.example.myjourney.server.ConnectThread
import com.example.myjourney.ui.adapter.BluetoothDeviceAdapter
import com.example.myjourney.ui.model.DataBluetoothList
import com.example.myjourney.ui.model.DataCut
import com.example.myjourney.ui.model.DataDevice
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/9/2022.
 */
@AndroidEntryPoint
class ScreenConnectionBluetooth : Fragment(R.layout.screen_connection_bluetooth) {
    private val binding: ScreenConnectionBluetoothBinding by viewBinding()
    private var myBluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 1000)
        }
        bluetoothSearch.launch(discoverableIntent)
    }
    private var bluetoothSearch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        loadServer()
        loadDevice()
    }
    private lateinit var adapter: BluetoothDeviceAdapter
    private val list = ArrayList<DataBluetoothList>()
    private lateinit var broadCast: MyBluetoothBroadCast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        broadCast = MyBluetoothBroadCast(requireContext())
        adapter = BluetoothDeviceAdapter(requireContext())
        requireActivity().let { fragmentActivity ->
            val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
            LocalBroadcastManager.getInstance(fragmentActivity).registerReceiver(broadCast, intentFilter)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getAllPermissions()
        loadViewDetails()
        loadViewSetOnclickListener()
        loadServer()
    }

    private fun loadViewDetails() {
        val bluetoothManager =
            if (Build.VERSION.SDK_INT >= 23) requireContext().getSystemService(BluetoothManager::class.java) else requireContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        myBluetoothAdapter = bluetoothManager.adapter

        myBluetoothAdapter?.let { adapter ->
            binding.switchBtn.isChecked = adapter.isEnabled
            if (adapter.isEnabled)
                loadDevice()
        }

        binding.rvList.adapter = adapter
    }

    private fun getAllPermissions() {
        if (Build.VERSION_CODES.S <= Build.VERSION.SDK_INT) {

            Dexter.withContext(requireContext()).withPermission(Manifest.permission.BLUETOOTH_CONNECT)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        showToast("Permission Granted")
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        showToast("Permission Denied")
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                        AlertDialog.Builder(requireActivity()).setTitle("Bluetooth")
                            .setMessage("Bluetooth connection permission")
                            .setNegativeButton("dismiss") { dialog, _ ->
                                dialog.dismiss()
                                token?.cancelPermissionRequest()
                            }.setPositiveButton("allow") { dialog, _ ->
                                dialog.dismiss()
                                token?.continuePermissionRequest()
                            }.show()
                    }
                })
            Dexter.withContext(requireContext()).withPermission(Manifest.permission.BLUETOOTH_SCAN)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        showToast("Permission Granted")
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        showToast("Permission Denied")
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                        AlertDialog.Builder(requireActivity()).setTitle("Bluetooth")
                            .setMessage("Bluetooth connection permission")
                            .setNegativeButton("dismiss") { dialog, _ ->
                                dialog.dismiss()
                                token?.cancelPermissionRequest()
                            }.setPositiveButton("allow") { dialog, _ ->
                                dialog.dismiss()
                                token?.continuePermissionRequest()
                            }.show()
                    }
                })
        }

        Dexter.withContext(requireContext()).withPermissions(arrayListOf(Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.BLUETOOTH))
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    showToast("Launch")
                }

                override fun onPermissionRationaleShouldBeShown(p0: MutableList<PermissionRequest>?, p1: PermissionToken?) {
                    p1?.continuePermissionRequest()
                }
            })
    }

    private fun loadViewSetOnclickListener() {
        binding.title.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        adapter.setItemClick { data ->
            val myUUID = UUID.fromString(Constants.MY_UUID)
            val mmSocket: BluetoothSocket? = data.device.createRfcommSocketToServiceRecord(myUUID)
            if (myBluetoothAdapter != null && mmSocket != null) {
                val clientService = ConnectThread(myBluetoothAdapter!!, requireContext(), mmSocket)
                clientService.start()
                clientService.setListener {
                    Timber.d("Accepted socked is " + it.toString())
                }
                clientService.setErrorListener {
                    Timber.d(it)
                }
            }
        }
        binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                bluetoothResult.launch(intent)
            } else {
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    myBluetoothAdapter?.cancelDiscovery()
                    myBluetoothAdapter?.disable()
                }
            }
        }
        binding.btnLoad.setOnClickListener {
            loadServer()
        }
    }


    private fun loadServer() {
        try {
            val myUUID = UUID.fromString(Constants.MY_UUID)
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                val socket = myBluetoothAdapter?.listenUsingRfcommWithServiceRecord(Constants.APP_NAME, myUUID)
                if (socket != null) {
                    val server = AcceptThread(socket)
                    server.start()
                    server.setListener {
                        Timber.d("Acceped socked " + it.toString())
                    }
                    server.setErrorListener {
                        Timber.d(it)
                    }
                }
            }
        } catch (e: Exception) {
            showToast(e.message.toString())
        }

    }

    private fun loadDevice() {
        lifecycleScope.launch {
            list.clear()
            if (Build.VERSION_CODES.S <= Build.VERSION.SDK_INT) {
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    val set = myBluetoothAdapter?.bondedDevices
                    if (set != null && set.isNotEmpty()) {
                        list.add(DataCut(0, "Connected device"))
                        for (item in set) {
                            list.add(DataDevice(list.size, item.name, item, item.address))
                        }
                    }
                }
            } else {
                val set = myBluetoothAdapter?.bondedDevices
                if (set != null && set.isNotEmpty()) {
                    list.add(DataCut(0, "Connected device"))
                    for (item in set) {
                        list.add(DataDevice(list.size, item.name, item, item.address))
                    }
                }
            }
            adapter.differ.submitList(list.toMutableList())
            list.add(DataCut(list.size, "Searching Devices"))
            startDiscovering()
            broadCast.setGetIdAction {
                list.size
            }
            broadCast.setException {
                showToast("Error")
                loadDevice()
            }
            broadCast.setAction { data ->
                list.add(data)
                adapter.differ.submitList(list.toMutableList())
            }
        }
    }

    private fun startDiscovering() {
        lifecycleScope.launchWhenStarted {
            myBluetoothAdapter?.let {
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                    if (it.isDiscovering)
                        it.cancelDiscovery()
                    var cond = true
                    while (cond) {
                        val isDiscovery = it.startDiscovery()
                        if (isDiscovery && it.isDiscovering)
                            cond = false
                        delay(1000)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { fragmentActivity ->
            LocalBroadcastManager.getInstance(fragmentActivity).unregisterReceiver(broadCast)
        }
    }
}