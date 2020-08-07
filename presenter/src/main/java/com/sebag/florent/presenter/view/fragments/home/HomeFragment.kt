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

    override fun layoutRes() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private fun initView(view: View) {
        sendBtn.setOnClickListener {
            val direction = HomeFragmentDirections.launchDetail(toSend.text.toString())
            view.findNavController().navigate(direction)
        }

        generateBtn.setOnClickListener {
            viewModel.generateJoke()
            viewModel.mJoke.observe(viewLifecycleOwner, Observer { joke ->
                joke?.let {
                    joke_text_view.text = it.jokeText
                }
            })
        }
    }
}