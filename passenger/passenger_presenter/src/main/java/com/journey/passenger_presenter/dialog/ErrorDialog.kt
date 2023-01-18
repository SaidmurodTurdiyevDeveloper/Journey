package com.journey.passenger_presenter.dialog

import android.app.AlertDialog
import android.content.Context
import com.journey.passenger_presenter.R

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/31/2023 5:54 PM for My Journey.
 */
class ErrorDialog(
    context: Context,
    message: String
) {
    private val dialog = AlertDialog.Builder(context)
        .setTitle(context.getString(com.journey.common_utils.R.string.text_error))
        .setMessage(message)
        .setPositiveButton(context.getString(R.string.text_close), null)
        .create()

    fun show() = dialog.show()

}