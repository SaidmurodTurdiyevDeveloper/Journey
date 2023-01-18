package com.journey.passenger_presenter.dialog

import android.app.AlertDialog
import android.content.Context
import com.journey.passenger_presenter.R

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/31/2023 5:54 PM for My Journey.
 */
class MessageDialog(
    context: Context,
    title: String,
    message: String
) {
    private val dialog = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(context.getString(com.journey.common_utils.R.string.text_Ok), null)
        .create()

    fun show() = dialog.show()
}