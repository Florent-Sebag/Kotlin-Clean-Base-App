package com.sebag.florent.presenter.view.fragments.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject

class AuthFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : AuthVM

    override fun layoutRes() = R.layout.fragment_auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            bindLoginBtn(it)
        }
    }

    private fun bindLoginBtn(v: View) {

        if (check_input())
            return

        layout_email.isErrorEnabled = false
        showLoadingDialog("Authenticating...")
        viewModel.logUser(input_email.text.toString(), input_password.text.toString())

        viewModel.mUser.observe(viewLifecycleOwner, Observer { user ->
            hideLoadingDialog()
            user?.let {
                showToastMessage("Success !")
                user.email?.let {
                    val direction = AuthFragmentDirections.goHome(it)
                    v.findNavController().navigate(direction)
                }
            }
        })

        viewModel.mError.observe(viewLifecycleOwner, Observer {
            hideLoadingDialog()
            showToastMessage(it)
        })
    }

    private fun check_input() : Boolean {
        var res = false

        if (!isValidEmail(input_email.text.toString())) {
            layout_email.error = "The email address is badly formatted"
            layout_email.isErrorEnabled = true
            res = true
        } else {
            layout_email.isErrorEnabled = false
        }

        if (!isValidPassword(input_password.text.toString())) {
            layout_password.error = "The password is badly formatted"
            layout_password.isErrorEnabled = true
            res = true
        } else {
            layout_password.isErrorEnabled = false
        }
        return res
    }

    private fun isValidEmail(target : String) : Boolean {
        return !TextUtils.isEmpty(target)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun isValidPassword(target: String) : Boolean {
        return target.length > 6
    }
}