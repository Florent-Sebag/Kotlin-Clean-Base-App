package com.sebag.florent.presenter.view.fragments.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.databinding.FragmentHomeBinding
import com.sebag.florent.presenter.view.base.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    @Inject
    lateinit var viewModel : HomeVM

//    EXEMPLE GET ARGUMENTS
//    private val args: HomeFragmentArgs by navArgs()


    override fun layoutRes() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeVM = viewModel

        viewModel.getEmail()
        checkLogout(view)
    }

    private fun checkLogout(view: View) {
        viewModel.isDisconnected.observe(viewLifecycleOwner, Observer { isDisconnected ->
            if (isDisconnected) {
                val direction = HomeFragmentDirections.goLogin()
                view.findNavController().navigate(direction)
            }
        })
    }
}