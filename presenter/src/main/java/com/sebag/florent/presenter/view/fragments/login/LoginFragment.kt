package com.sebag.florent.presenter.view.fragments.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : LoginVM

    override fun layoutRes() = R.layout.fragment_auth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            bindLoginBtn(it)
        }
    }

    private fun bindLoginBtn(v: View) {

        if (errorEmailPassword())
            return

        showLoadingDialog("Authenticating...")
        viewModel.logUser(input_email.text.toString(), input_password.text.toString())
        viewModel.mSuccess.observe(viewLifecycleOwner, Observer { _ ->
            hideLoadingDialog()
            val direction = AuthFragmentDirections.goHome()
            v.findNavController().navigate(direction)
        })

        viewModel.mError.observe(viewLifecycleOwner, Observer {
            hideLoadingDialog()
            showToastMessage(it)
        })
    }

    private fun errorEmailPassword() : Boolean {

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