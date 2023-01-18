package com.journey.passenger_presenter.screen.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.journey.common_utils.other.Constants
import com.journey.passenger_domen.model.ImagesAdvertise
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.databinding.ScreenItemAdvertiseBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/5/2022.
 */
@AndroidEntryPoint
class FragmentItemAdvertise(private var listener: (Int?) -> Unit) :
    Fragment(R.layout.screen_item_advertise) {
    private var data: ImagesAdvertise? = null
    private var binding: ScreenItemAdvertiseBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = ScreenItemAdvertiseBinding.bind(view)
        loadData()
        loadDetails()
        binding?.cv?.setOnClickListener {
            listener.invoke(data?.id)
        }
    }

    private fun loadDetails() {
        data?.let {
            binding?.apply {
                Glide.with(this@FragmentItemAdvertise).load(it.image).into(mainImage)
                Glide.with(this@FragmentItemAdvertise).load(it.images.first()).into(mainImage)
                Glide.with(this@FragmentItemAdvertise).load(it.images[1]).into(imageTwo)
                Glide.with(this@FragmentItemAdvertise).load(it.images[2]).into(imageThree)
                Glide.with(this@FragmentItemAdvertise).load(it.images[3]).into(imageFour)
            }
        }
    }

    private fun loadData() {
        data = if (Build.VERSION.SDK_INT > 32) {
            requireArguments().getParcelable(Constants.MOVE_DATA, ImagesAdvertise::class.java)
        } else {
            requireArguments().getParcelable(Constants.MOVE_DATA)
        }
    }
}