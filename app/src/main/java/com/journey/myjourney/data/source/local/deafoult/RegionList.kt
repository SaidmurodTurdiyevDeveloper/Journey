package com.journey.myjourney.data.source.local.deafoult

import android.content.Context
import com.journey.myjourney.R
import com.journey.myjourney.data.model.RegionData_Full
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
class RegionList @Inject constructor(@ApplicationContext private var context: Context) {
    private var list = ArrayList<RegionData_Full>()

    fun getList(): List<RegionData_Full> {
        return list
    }

    init {
        if (list.isEmpty()) {
            list.add(RegionData_Full(0, R.drawable.andijon, context.getString(R.string.text_andijon), context.getString(R.string.text_andijon_description)))
            list.add(RegionData_Full(1, R.drawable.buxoro, context.getString(R.string.text_bukhara), context.getString(R.string.text_bukhara_description)))
            list.add(RegionData_Full(2, R.drawable.fargona, context.getString(R.string.text_fergana), context.getString(R.string.text_fergana_description)))
            list.add(RegionData_Full(3, R.drawable.jizzax, context.getString(R.string.text_jizzakh), context.getString(R.string.text_jizzakh_description)))
            list.add(RegionData_Full(4, R.drawable.xorazm, context.getString(R.string.text_khorazm), context.getString(R.string.text_khorazm_description)))
            list.add(RegionData_Full(5, R.drawable.namangan, context.getString(R.string.text_namangan), context.getString(R.string.text_namangan_description)))
            list.add(RegionData_Full(6, R.drawable.navoi, context.getString(R.string.text_navoi), context.getString(R.string.text_navoi_description)))
            list.add(RegionData_Full(7, R.drawable.qashqadaryo, context.getString(R.string.text_qashqadaro), context.getString(R.string.text_qashqadaro_description)))
            list.add(RegionData_Full(8, R.drawable.qoraqalpogiston, context.getString(R.string.text_qoraqalpoq), context.getString(R.string.text_qorqalpoq_description)))
            list.add(RegionData_Full(9, R.drawable.samarqand, context.getString(R.string.text_samarkand), context.getString(R.string.text_samarkand_description)))
            list.add(RegionData_Full(10, R.drawable.sirdaryo, context.getString(R.string.text_sirdaryo), context.getString(R.string.text_sirdaryo)))
            list.add(RegionData_Full(11, R.drawable.surxondaryo, context.getString(R.string.text_surkhandaryo), context.getString(R.string.text_surkhandaryo_description)))
            list.add(RegionData_Full(12, R.drawable.toshkent, context.getString(R.string.text_tashkent), context.getString(R.string.text_tashkent_description)))
            list.add(RegionData_Full(-1, R.drawable.uzbekistan, context.getString(R.string.text_uzbekistan), context.getString(R.string.text_uzbekistan_description)))
        }
    }
}