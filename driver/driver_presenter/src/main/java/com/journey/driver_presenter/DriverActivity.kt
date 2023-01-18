package com.journey.driver_presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.journey.common_utils.Navigator

class DriverActivity : AppCompatActivity() {
    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, DriverActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)
    }
}

object GoToDriverActivity : Navigator {
    override fun navigate(activity: Activity) {
        DriverActivity.launchActivity(activity)
    }
}