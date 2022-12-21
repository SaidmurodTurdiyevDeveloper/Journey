package com.example.myjourney.other.extention

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible

/**
 * Saidmurod Turdiyev writes this for All Project (SMT)
 */

fun ViewGroup.inflate(resId:Int):View{
    return LayoutInflater.from(context).inflate(resId,this,false)
}
