package com.journey.common_data.source.local.default

import android.content.Context
import com.journey.common_data.R
import com.journey.common_data.module.RegionDTO

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
class DefaultRegionList  (private val context: Context) {
    private var list = ArrayList<RegionDTO>()

    fun getList(): List<RegionDTO> {
        return list
    }

    init {
        if (list.isEmpty()) {
            list.add(RegionDTO(0, R.drawable.andijon, context.getString(R.string.text_andijon), context.getString(R.string.text_andijon_description)))
            list.add(RegionDTO(1, R.drawable.buxoro, context.getString(R.string.text_bukhara), context.getString(R.string.text_bukhara_description)))
            list.add(RegionDTO(2, R.drawable.fargona, context.getString(R.string.text_fergana), context.getString(R.string.text_fergana_description)))
            list.add(RegionDTO(3, R.drawable.jizzax, context.getString(R.string.text_jizzakh), context.getString(R.string.text_jizzakh_description)))
            list.add(RegionDTO(4, R.drawable.xorazm, context.getString(R.string.text_khorazm), context.getString(R.string.text_khorazm_description)))
            list.add(RegionDTO(5, R.drawable.namangan, context.getString(R.string.text_namangan), context.getString(R.string.text_namangan_description)))
            list.add(RegionDTO(6, R.drawable.navoi, context.getString(R.string.text_navoi), context.getString(R.string.text_navoi_description)))
            list.add(RegionDTO(7, R.drawable.qashqadaryo, context.getString(R.string.text_qashqadaro), context.getString(R.string.text_qashqadaro_description)))
            list.add(RegionDTO(8, R.drawable.qoraqalpogiston, context.getString(R.string.text_qoraqalpoq), context.getString(R.string.text_qorqalpoq_description)))
            list.add(RegionDTO(9, R.drawable.samarqand, context.getString(R.string.text_samarkand), context.getString(R.string.text_samarkand_description)))
            list.add(RegionDTO(10, R.drawable.sirdaryo, context.getString(R.string.text_sirdaryo), context.getString(R.string.text_sirdaryo)))
            list.add(RegionDTO(11, R.drawable.surxondaryo, context.getString(R.string.text_surkhandaryo), context.getString(R.string.text_surkhandaryo_description)))
            list.add(RegionDTO(12, R.drawable.toshkent, context.getString(R.string.text_tashkent), context.getString(R.string.text_tashkent_description)))
            list.add(RegionDTO(-1, R.drawable.uzbekistan, context.getString(R.string.text_uzbekistan), context.getString(R.string.text_uzbekistan_description)))
        }
    }
}