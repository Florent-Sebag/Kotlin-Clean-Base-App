package com.sebag.florent.presenter.view.fragments.auth

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class AuthFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : AuthVM

    override fun layoutRes() = R.layout.fragment_auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {

            showLoadingDialog("Authenticating...")
            viewModel.logUser(input_email.text.toString(), input_password.text.toString())

            viewModel.mUser.observe(viewLifecycleOwner, Observer { user ->
                hideLoadingDialog()
                user?.let {
                    showToastMessage("Success !")
                    val direction = AuthFragmentDirections.goHome(it.email!!)
                    view.findNavController().navigate(direction)
                }
            })

            viewModel.mError.observe(viewLifecycleOwner, Observer {
                hideLoadingDialog()
                showToastMessage(it)
            })

        }
    }
}