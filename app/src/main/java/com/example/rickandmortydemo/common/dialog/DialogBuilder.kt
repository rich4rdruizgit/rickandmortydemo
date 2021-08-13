package com.example.projectarchitecture.common.dialog

import android.app.Dialog
import android.content.Context


class DialogBuilder {

    companion object {

        fun showMessageLoadingDialog(context: Context, dataBuilder: DataBuilderDialog): Dialog {

            if (dataBuilder.typeDialog != TypeDialog.LOADING
                && dataBuilder.typeDialog != TypeDialog.MESSAGE) {
                throw DialogBuilderException("This dialog only accepts type loading and message")
            }

            val dialog = Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            dialog.setTitle(dataBuilder.message)
            dialog.setCancelable(dataBuilder.cancelable)

            return dialog
        }
    }
}