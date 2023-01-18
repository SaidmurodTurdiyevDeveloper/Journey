package com.example.myjourney.other.extention

import android.content.BroadcastReceiver
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatCallback
import com.example.myjourney.ui.activity.MainActivity


/**
 * Saidmurod Turdiyev writes this for All Project (SMT)
 */
fun AppCompatActivity.changeStatusBarColor(color: Int) {
    window.statusBarColor = color
}

fun AppCompatActivity.changeNavigationBarColor(color: Int) {
    window.navigationBarColor = color
}