package com.journey.myjourney.ui.screen.service

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.myjourney.R
import com.journey.myjourney.data.model.ServiceData_Full
import com.journey.myjourney.databinding.ScreenServiceBinding
import com.journey.myjourney.ui.adapter.ServiceAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServiceScreen : Fragment(R.layout.screen_service) {
    private val binding: ScreenServiceBinding by viewBinding()
    private var adapter = ServiceAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewDetails()
    }

    private fun loadViewDetails() {
        binding.rv.adapter=adapter
        var list=ArrayList<ServiceData_Full>()
        list.add(ServiceData_Full(0,"Service one", emptyList()))
        list.add(ServiceData_Full(1,"Service two", emptyList()))
        list.add(ServiceData_Full(2,"Service three", emptyList()))
        list.add(ServiceData_Full(3,"Service four", emptyList()))
        adapter.differ.submitList(list)
    }

}