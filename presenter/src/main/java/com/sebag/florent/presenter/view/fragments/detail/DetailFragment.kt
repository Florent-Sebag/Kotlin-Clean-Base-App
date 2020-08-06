package com.sebag.florent.presenter.view.fragments.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : DetailVM

    private val args: DetailFragmentArgs by navArgs()

    override fun layoutRes() = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_text.text = args.userInput
    }
}