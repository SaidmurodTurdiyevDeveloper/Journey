package com.journey.passenger_presenter.screen.setting

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.common_utils.bluetooth.BluetoothController
import com.journey.common_utils.broadcast.BluetoothReceiver
import com.journey.common_utils.other.extention.showSnackBar
import com.journey.common_utils.other.extention.showToast
import com.journey.passenger_presenter.adapter.BluetoothDeviceAdapter
import com.journey.common_utils.other.model.DataBluetoothList
import com.journey.common_utils.other.model.DataCut
import com.journey.common_utils.other.model.DataDevice
import com.journey.passenger_presenter.PassengerActivity
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.databinding.ScreenConnectionBluetoothBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/9/2022.
 */
@AndroidEntryPoint
class ScreenConnectionBluetooth : Fragment(R.layout.screen_connection_bluetooth) {
    private val binding: ScreenConnectionBluetoothBinding by viewBinding()
    private var myBluetoothAdapter: BluetoothAdapter? = null
    private var myBluetoothController: BluetoothController? = null
    private var receiver: BluetoothReceiver? = null
    private var bluetoothResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        }
        bluetoothSearch.launch(discoverableIntent)
    }
    private var bluetoothSearch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        loadDevice()
    }
    private lateinit var adapter: BluetoothDeviceAdapter
    private val list = ArrayList<DataBluetoothList>()
    private val searchingList = ArrayList<DataBluetoothList>()
    private var autoSearchCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = BluetoothDeviceAdapter(requireContext())
        val myActivity = requireActivity()
        if (myActivity is PassengerActivity) {
            receiver = myActivity.getReceiver()
            val bluetoothManager =
                if (Build.VERSION.SDK_INT >= 23) requireContext().getSystemService(BluetoothManager::class.java) else requireContext().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            myBluetoothAdapter = bluetoothManager.adapter
            if (myBluetoothAdapter != null && receiver != null) {
                myBluetoothController = BluetoothController(requireActivity(), myBluetoothAdapter!!, receiver!!)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getAllPermissions()
        loadViewDetails()
        loadViewSetOnclickListener()
    }

    private fun loadViewDetails() {
        myBluetoothAdapter?.let { adapter ->
            binding.switchBtn.isChecked = adapter.isEnabled
            if (adapter.isEnabled)
                loadDevice()
        }
        myBluetoothController?.setOnBluetoothOnListener {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            bluetoothResult.launch(intent)
        }
        myBluetoothController?.setOnBluetoothOffListener {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                if (myBluetoothAdapter?.isDiscovering == true)
                    myBluetoothAdapter?.cancelDiscovery()
                myBluetoothAdapter?.disable()
            }
        }
        myBluetoothController?.setOnDiscoveringListener {

        }
        myBluetoothController?.setShowMessageListener {

        }
        receiver?.setGetIdAction {
            list.size
        }
        receiver?.setAddNewDeviceListener { data ->
            var cond = true
            searchingList.forEach { d ->
                if (d is DataDevice)
                    if (d.address == data.address)
                        cond = false
            }
            if (cond) {
                searchingList.add(data)
                list.add(data)
                adapter.differ.submitList(list.toMutableList())
            }
        }

        receiver?.setExceptionListener { message ->
            errorDialog(message)
        }

        receiver?.setDiscoveryEndListener {
            if (autoSearchCount > 3) {
                startDiscovering()
                autoSearchCount++
            } else {
                binding.btnRefresh.isVisible = true
            }
        }

        receiver?.setPairingEndListener {

        }

        receiver?.setStatusChangeListener {

        }

        binding.rvList.adapter = adapter
    }

    private fun getAllPermissions() {
        Dexter.withContext(requireContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    showToast("Access Fine Location permission granted")
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    errorDialog("Access Fine Location permission denied")
                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                    AlertDialog.Builder(requireActivity()).setTitle("Bluetooth")
                        .setMessage("Access fine location permission")
                        .setNegativeButton("dismiss") { dialog, _ ->
                            dialog.dismiss()
                            token?.cancelPermissionRequest()
                        }.setPositiveButton("allow") { dialog, _ ->
                            dialog.dismiss()
                            token?.continuePermissionRequest()
                        }.show()
                }
            })
            .check()
    }

    private fun errorDialog(message: String) {

    }

    private fun loadViewSetOnclickListener() {
        binding.title.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        adapter.setItemClick { data ->
            if (myBluetoothController?.isAlreadyPaired(data.device)==true)
                showSnackBar("This device already paired")
            else{
                val outCome=myBluetoothController?.pair(data.device)
                if (outCome==true){
                    showProgressDialog()
                }
            }
        }
        binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                myBluetoothController?.turnOnBluetooth()
            } else {
                myBluetoothController?.turnOffBluetooth()
            }
        }
        binding.btnRefresh.setOnClickListener {
            autoSearchCount = 0
            myBluetoothController?.startDiscovery()
        }
    }
    private fun showProgressDialog(){

    }


    private fun loadDevice() {
        lifecycleScope.launch {
            list.clear()
            searchingList.clear()
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
            myBluetoothController?.startDiscovery()
        }
    }

    private fun startDiscovering() {
        lifecycleScope.launchWhenStarted {
            myBluetoothAdapter?.let {
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                    if (it.isDiscovering)
                        it.cancelDiscovery()
                    it.startDiscovery()
                } else {
                    if (it.isDiscovering)
                        it.cancelDiscovery()
                    it.startDiscovery()
                }
            }
        }
    }
}