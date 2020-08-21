package com.sebag.florent.presenter.view.fragments.auth.utils

import android.text.TextUtils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object CheckEmailPass {

    fun errorEmailPassword(
                isBadInput: (editText: TextInputEditText,
                        layout: TextInputLayout,
                        onErrorMsg : String,
                        checkFun: (input: String) -> Boolean) -> (Boolean),
                input_email: TextInputEditText,
                layout_email: TextInputLayout,
                input_password: TextInputEditText,
                layout_password: TextInputLayout
    ) : Boolean {

        val badEmail =  isBadInput(input_email, layout_email,
            "The email address is badly formatted") {
            !TextUtils.isEmpty(it)
                    && android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
        }

        val badPassword = isBadInput(input_password, layout_password,
            "The password is badly formatted") {
            it.length > 6
        }

        return (badEmail || badPassword)
    }
}