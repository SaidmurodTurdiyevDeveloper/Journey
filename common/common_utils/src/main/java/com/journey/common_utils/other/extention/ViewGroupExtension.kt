package com.journey.common_utils.other.extention

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Saidmurod Turdiyev writes this for All Project (SMT)
 */

fun ViewGroup.inflate(resId:Int):View{
    return LayoutInflater.from(context).inflate(resId,this,false)
}
