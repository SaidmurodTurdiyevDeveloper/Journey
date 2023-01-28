package com.journey.myjourney.ui.screen.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.databinding.ScreenSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenSetting : Fragment(R.layout.screen_setting) {
    private val binding: ScreenSettingBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
    }

    private fun loadViewClickable() {
        binding.btnAbout.setOnClickListener {
        }
        binding.btnHelp.setOnClickListener {
        }
        binding.btnLanguage.setOnClickListener {
        }
        binding.btnConnection.setOnClickListener {
        }
    }
}