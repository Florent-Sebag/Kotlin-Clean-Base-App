package com.sebag.florent.presenter.view.fragments.auth.registration

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.databinding.FragmentRegistrationBinding
import com.sebag.florent.presenter.view.base.BaseFragment
import com.sebag.florent.presenter.view.fragments.auth.utils.CheckEmailPass
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.input_email
import kotlinx.android.synthetic.main.fragment_registration.input_password
import kotlinx.android.synthetic.main.fragment_registration.layout_email
import kotlinx.android.synthetic.main.fragment_registration.layout_password
import javax.inject.Inject

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_registration

    @Inject
    lateinit var viewModel : RegistrationVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        btn_registrate.setOnClickListener {
            setRegistrateBtn(view)
        }
    }

    private fun setRegistrateBtn(v: View) {
        if (CheckEmailPass.errorEmailPassword(::isBadInput, input_email, layout_email,
                input_password, layout_password))
            return
        showLoadingDialog("Registrating...")
        viewModel.registrateUser(input_email.text.toString(), input_password.text.toString())
    }

    private fun bindView(v: View) {
        viewModel.mSuccess.observe(viewLifecycleOwner, Observer { _ ->
            hideLoadingDialog()
            showToastMessage("Success !")
            val direction = RegistrationFragmentDirections.goHome()
            v.findNavController().navigate(direction)
        })

        viewModel.mError.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                hideLoadingDialog()
                showToastMessage(it)
            }
        })
    }
}