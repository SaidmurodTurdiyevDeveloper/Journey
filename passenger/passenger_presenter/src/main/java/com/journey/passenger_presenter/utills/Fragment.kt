package com.journey.passenger_presenter.utills

import androidx.fragment.app.Fragment
import com.journey.passenger_presenter.R
import com.journey.passenger_presenter.dialog.ActionDialog
import com.journey.passenger_presenter.dialog.ErrorDialog
import com.journey.passenger_presenter.dialog.MessageDialog

/**
 * Created by Saidmurod Turdiyev (S.M.T) on 1/31/2023 5:54 PM for My Journey.
 */
fun Fragment.showErrorDialog(message: String?) {
    val dialog = ErrorDialog(
        requireContext(),
        message = message ?: getString(com.journey.common_utils.R.string.text_error_message)
    )
    dialog.show()
}

fun Fragment.showMessageDialog(message: String, title: String = "Message") {
    val dialog = MessageDialog(
        requireContext(),
        message = message,
        title = title
    )
    dialog.show()
}

fun Fragment.showActionDialog(title: String, message: String, action: () -> Unit) {
    val dialog = ActionDialog(
        requireContext(),
        message = message,
        title = title,
        action = action
    )
    dialog.show()
}