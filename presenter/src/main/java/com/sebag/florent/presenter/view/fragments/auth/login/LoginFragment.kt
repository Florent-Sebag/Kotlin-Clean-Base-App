package com.sebag.florent.presenter.view.fragments.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.view.base.BaseFragment
import com.sebag.florent.presenter.view.fragments.auth.utils.CheckEmailPass
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : LoginVM

    private lateinit var googleLoginIntent : Intent

    override fun layoutRes() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("gnah","Onviewcreated")
        bindBtns()
        bindExternalConnection()
        bindOnConnectionResult(view)
    }

    private fun bindBtns() {
        btn_login.setOnClickListener {
            bindLoginBtn(it)
        }

        link_signup.setOnClickListener { v ->
            val direction = LoginFragmentDirections.goRegistration()
            v.findNavController().navigate(direction)
        }
    }

    private fun bindLoginBtn(v: View) {

        if (CheckEmailPass.errorEmailPassword(::isBadInput, input_email, layout_email,
                input_password, layout_password))
            return

        showLoadingDialog("Authenticating...")
        viewModel.logUser(input_email.text.toString(), input_password.text.toString())
    }

    private fun bindExternalConnection() {
        btn_login_fb.fragment = this
        btn_login_google.setOnClickListener {
            startActivityForResult(googleLoginIntent, 1)
        }
        googleLoginIntent = viewModel.bindExternalConnection(this.requireActivity(), btn_login_fb)
    }

    private fun bindOnConnectionResult(v: View) {
        viewModel.mSuccess.observe(viewLifecycleOwner, Observer { _ ->
            hideLoadingDialog()
            val direction = LoginFragmentDirections.goHome()
            v.findNavController().navigate(direction)
        })

        viewModel.mError.observe(viewLifecycleOwner, Observer {
            hideLoadingDialog()
            showToastMessage(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showLoadingDialog("Authenticating...")
        viewModel.onExternalConnectionResult(requestCode, resultCode, data)
    }
}