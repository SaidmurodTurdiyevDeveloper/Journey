package com.journey.myjourney.ui.screen.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.journey.myjourney.R
import com.journey.myjourney.databinding.ScreenSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenSetting : Fragment(R.layout.screen_setting) {
    private val binding: ScreenSettingBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViewClickable()
    }

    private fun loadViewClickable() {
        binding.btnAbout.setOnClickListener {
            findNavController().navigate(R.id.action_setting_screen_to_screenAbout)
        }
        binding.btnHelp.setOnClickListener {
            findNavController().navigate(R.id.action_setting_screen_to_screenHelp)
        }
        binding.btnLanguage.setOnClickListener {
            findNavController().navigate(R.id.action_setting_screen_to_screenLanguage)
        }
        binding.btnConnection.setOnClickListener {
            findNavController().navigate(R.id.action_setting_screen_to_screenConnectionBluetooth)
        }
    }
}