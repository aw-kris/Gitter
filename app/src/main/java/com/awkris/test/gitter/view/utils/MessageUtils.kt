package com.awkris.test.gitter.view.utils

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.awkris.test.gitter.R
import com.google.android.material.snackbar.Snackbar

private fun Snackbar.showErrorMessage() {
    setColor(view, R.color.snackbar_error_message_background, R.color.white)
    show()
}

private fun Snackbar.showSuccessMessage() {
    setColor(view, R.color.snackbar_success_message_background, R.color.white)
    show()
}

fun Snackbar.showMessage(isErrorMessage: Boolean) {
    if (isErrorMessage) showErrorMessage() else showSuccessMessage()
}

private fun setColor(snackbarView: View, @ColorRes backgroundColor: Int, @ColorRes textColor: Int) {
    val context = snackbarView.context
    val textView =
        snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(ContextCompat.getColor(context, textColor))
    snackbarView.setBackgroundColor(
        ContextCompat.getColor(
            context,
            backgroundColor
        )
    )
}