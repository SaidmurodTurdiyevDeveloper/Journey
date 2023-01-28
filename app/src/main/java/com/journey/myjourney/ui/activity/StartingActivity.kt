package com.journey.myjourney.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.journey.common_utils.activity.Activities
import com.journey.common_utils.navigator.Navigator
import com.journey.common_utils.other.extention.showToast
import com.journey.myjourney.R
import com.journey.myjourney.navigator.DefaultNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StartingActivity : AppCompatActivity() {

    @Inject
    lateinit var provider: Navigator.Provider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)
        lifecycleScope.launch {
            runCatching {
                provider.getActivities(Activities.PassengerActivity).navigate(this@StartingActivity)
            }.onSuccess {
                delay(100)
                finish()
            }.onFailure {
                showToast(it.message.toString())
            }
        }
    }
}