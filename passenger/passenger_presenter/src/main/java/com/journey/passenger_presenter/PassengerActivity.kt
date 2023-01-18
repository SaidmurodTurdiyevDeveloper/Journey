package com.journey.passenger_presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.journey.common_utils.Navigator

class PassengerActivity : AppCompatActivity() {
    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, PassengerActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger)
    }
}

object GoToPassengerActivity : Navigator {
    override fun navigate(activity: Activity) {
        PassengerActivity.launchActivity(activity)
    }
}