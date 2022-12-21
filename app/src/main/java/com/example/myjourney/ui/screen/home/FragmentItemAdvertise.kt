package com.example.myjourney.ui.screen.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myjourney.R
import com.example.myjourney.databinding.ScreenItemAdvertiseBinding
import com.example.myjourney.domen.model.ImagesAdvertise
import com.example.myjourney.other.Constants
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/5/2022.
 */
@AndroidEntryPoint
class FragmentItemAdvertise : Fragment(R.layout.screen_item_advertise) {
    private val binding: ScreenItemAdvertiseBinding by viewBinding()
    private var data: ImagesAdvertise? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData()
        loadDetails()
    }

    private fun loadDetails() {
        data?.let { advertise ->
            binding.mainImage.setImageResource(advertise.image)
            if (advertise.images.isNotEmpty())
                binding.imageTwo.setImageResource(advertise.images.first())
            if (advertise.images.size > 1)
                binding.imageThree.setImageResource(advertise.images[1])
            if (advertise.images.size > 2)
                binding.imageFour.setImageResource(advertise.images[2])
        }
    }

    private fun loadData() {
        data = if (Build.VERSION_CODES.TIRAMISU > Build.VERSION.SDK_INT)
            requireArguments().getParcelable(Constants.MOVE_DATA)
        else
            requireArguments().getParcelable(Constants.MOVE_DATA, ImagesAdvertise::class.java)
    }
}