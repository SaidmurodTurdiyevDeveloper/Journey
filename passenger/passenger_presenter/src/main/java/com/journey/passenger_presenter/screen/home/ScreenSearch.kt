package com.journey.passenger_presenter.screen.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.passenger_presenter.adapter.PlaceSearchAdapter
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.databinding.ScreenSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScreenSearch : Fragment(R.layout.screen_search) {
    private val binding: ScreenSearchBinding by viewBinding()
    private var adapter = PlaceSearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
        loadViewDetails()
    }

    private fun loadViewDetails() {
        binding.etSearch.addTextChangedListener {
            lifecycleScope.launch {
                val text = binding.etSearch.text.toString().trim()
                delay(700)
                val newText = binding.etSearch.text.toString().trim()
                if (text == newText) {
                    adapter.filter(newText)
                }
            }
        }
        binding.list.adapter = adapter
    }

    private fun loadViewClickable() {
        binding.title.setNavigationOnClickListener {
        }
        adapter.setItemClick { id ->
        }
    }

    private fun showError(message: String) {

    }

    private fun openLoading() {

    }
}