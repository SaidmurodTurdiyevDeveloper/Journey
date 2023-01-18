package com.journey.common_data.source.local.default

import android.content.Context
import com.journey.common_data.R
import com.journey.common_data.module.RegionDTO
import com.journey.common_utils.other.RegionConstant

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 12/1/2022.
 */
class DefaultRegionList(private val context: Context) {
    private var list = ArrayList<RegionDTO>()

    fun getList(): List<RegionDTO> {
        return ArrayList<RegionDTO>(list)
    }

    init {
        if (list.isEmpty()) {
            list.add(
                RegionDTO(
                    RegionConstant.ANDIJAN_ID,
                    R.drawable.andijan,
                    context.getString(R.string.text_andijan),
                    context.getString(R.string.text_andijan_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.BUKHARA_ID,
                    R.drawable.bukhara,
                    context.getString(R.string.text_bukhara),
                    context.getString(R.string.text_bukhara_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.FERGANA_ID,
                    R.drawable.fergana,
                    context.getString(R.string.text_fergana),
                    context.getString(R.string.text_fergana_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.JIZZAKH_ID,
                    R.drawable.jizzakh,
                    context.getString(R.string.text_jizzakh),
                    context.getString(R.string.text_jizzakh_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.KHOREZM_ID,
                    R.drawable.khorezm,
                    context.getString(R.string.text_khorezm),
                    context.getString(R.string.text_khorezm_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.NAMANGAN_ID,
                    R.drawable.namangan,
                    context.getString(R.string.text_namangan),
                    context.getString(R.string.text_namangan_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.NAVOI_ID,
                    R.drawable.navoi,
                    context.getString(R.string.text_navoi),
                    context.getString(R.string.text_navoi_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.KASHKADARYA_ID,
                    R.drawable.kashkadarya,
                    context.getString(R.string.text_kashkadarya),
                    context.getString(R.string.text_kashkadarya_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.KARAKALPAK_ID,
                    R.drawable.karakalpak,
                    context.getString(R.string.text_karakalpak),
                    context.getString(R.string.text_karakalpak_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.SAMARKAND_ID,
                    R.drawable.samarkand,
                    context.getString(R.string.text_samarkand),
                    context.getString(R.string.text_samarkand_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.SIRDARYA_ID,
                    R.drawable.sirdarya,
                    context.getString(R.string.text_sirdarya),
                    context.getString(R.string.text_sirdarya_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.SURKHANDARYA_ID,
                    R.drawable.surkhandarya,
                    context.getString(R.string.text_surkhandarya),
                    context.getString(R.string.text_surkhandarya_description)
                )
            )
            list.add(
                RegionDTO(
                    RegionConstant.TASHKENT_ID,
                    R.drawable.tashkent,
                    context.getString(R.string.text_tashkent),
                    context.getString(R.string.text_tashkent_description)
                )
            )
            list.add(
                RegionDTO(
                    -1,
                    R.drawable.uzbekistan,
                    context.getString(R.string.text_uzbekistan),
                    context.getString(R.string.text_uzbekistan_description)
                )
            )
        }
    }
}