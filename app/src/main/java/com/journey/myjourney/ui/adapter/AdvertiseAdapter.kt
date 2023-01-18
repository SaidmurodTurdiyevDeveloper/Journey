package com.journey.myjourney.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.journey.myjourney.domen.model.ImagesAdvertise
import com.journey.myjourney.other.Constants
import com.journey.myjourney.other.extention.putArguments
import com.journey.myjourney.ui.screen.home.FragmentItemAdvertise

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/5/2022.
 */
class AdvertiseAdapter(activity: FragmentActivity, private val list: List<ImagesAdvertise>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        return FragmentItemAdvertise().putArguments {
            putParcelable(Constants.MOVE_DATA, list[position])
        }
    }
}