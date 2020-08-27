package com.sebag.florent.presenter.view.fragments.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : HomeVM

//    EXEMPLE GET ARGUMENTS
//    private val args: HomeFragmentArgs by navArgs()

    override fun layoutRes() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        listenVM(view)
    }

    private fun initView() {
        generateBtn.text = viewModel.getEmail()

        generateBtn.setOnClickListener {
            viewModel.generateJoke()
        }
        logoutBtn.setOnClickListener {
            viewModel.logoutUser()
        }
    }

    private fun listenVM(view: View) {
        viewModel.mJoke.observe(viewLifecycleOwner, Observer { joke ->
            joke?.let {
                joke_text_view.text = it.jokeText
            }
        })
        viewModel.isDisconnected.observe(viewLifecycleOwner, Observer { isDisconnected ->
            if (isDisconnected) {
                //TODO Close this fragment
                val direction = HomeFragmentDirections.goLogin()
                view.findNavController().navigate(direction)
            }
        })
    }
}