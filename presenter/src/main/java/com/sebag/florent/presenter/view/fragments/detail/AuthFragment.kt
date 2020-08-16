package com.sebag.florent.presenter.view.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject

class AuthFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : AuthVM

    private val args: AuthFragmentArgs by navArgs()

    override fun layoutRes() = R.layout.fragment_auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_text.text = args.userInput
        viewModel.coucou()
    }
}