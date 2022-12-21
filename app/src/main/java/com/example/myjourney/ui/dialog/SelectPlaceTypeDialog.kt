package com.example.myjourney.ui.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myjourney.R
import com.example.myjourney.databinding.ScreenSelectBottomsheetBinding
import com.example.myjourney.other.Constants
import com.example.myjourney.other.type.sendOneParametreBlock
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 20/11/2022.
 */
class SelectPlaceTypeDialog(private var placeName: String) : BottomSheetDialogFragment() {
    private var _binding: ScreenSelectBottomsheetBinding? = null
    private val binding: ScreenSelectBottomsheetBinding get() = _binding!!
    private var onClick: sendOneParametreBlock<String>? = null
    fun setOnClickListener(block: sendOneParametreBlock<String>) {
        onClick = block
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenSelectBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvTitle.text = placeName + getString(R.string.text_select_bottoms)
        binding.btnAll.setOnClickListener {
            onClick?.invoke(Constants.All)
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnCamp.setOnClickListener {
            onClick?.invoke(Constants.ENCAMPMENT)
            dismiss()
        }
        binding.btnHistorical.setOnClickListener {
            onClick?.invoke(Constants.BUILDING)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}