package com.example.myjourney.data.source.local.shared

import android.annotation.SuppressLint
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 16/11/2022.
 */
class SharedDatabase @Inject constructor(@ApplicationContext private var context: Context) {
    private var data =
        context.getSharedPreferences("MySharedDdMyJourney", Context.MODE_PRIVATE)
    private var editor = data.edit()

//    companion object {
//        @SuppressLint("StaticFieldLeak")
//        var instance: SharedDatabase? = null
//        fun getInstances(context: Context): SharedDatabase {
//            return instance ?: let {
//                val t = SharedDatabase(context)
//                instance = t
//                t
//            }
//        }
//    }


    fun setBooleanData(myStringObjectData: String, data: Boolean) {
        editor.putBoolean(myStringObjectData, data)
        editor.apply()
    }

    fun getBooleanData(myStringObjectData: String): Boolean =
        this.data.getBoolean(myStringObjectData, false)

    fun setStringData(myStringObjectData: String, data: String) {
        editor.putString(myStringObjectData, data)
        editor.apply()
    }

    var firstEnter: Boolean
        get() = getBooleanData("first_enter_boolean")
        set(value) = setBooleanData("first_enter_boolean", value)
    var appLanguage: Int
        get() = getIntData("app_language_integer")
        set(value) = setIntData("app_language_integer", value)

    var currentRegion: Int
        get() = getIntData("current_region_integer")
        set(value) = setIntData("current_region_integer", value)

    fun getStringData(myStringObjectData: String): String? =
        this.data.getString(myStringObjectData, "")

    fun setLongData(myLongObjectData: String, data: Long) {
        editor.putLong(myLongObjectData, data)
        editor.apply()
    }

    fun getLongData(myLongObjectData: String): Long = this.data.getLong(myLongObjectData, 0L)

    fun setIntData(myStringObjectData: String, data: Int) {
        editor.putInt(myStringObjectData, data)
        editor.apply()
    }

    fun getIntData(myStringObjectData: String): Int = this.data.getInt(myStringObjectData, -1)
}