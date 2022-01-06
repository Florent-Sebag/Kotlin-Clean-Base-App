package com.sebag.florent.presenter.view.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sebag.florent.presenter.R
import com.sebag.florent.presenter.databinding.FragmentHomeBinding
import com.sebag.florent.presenter.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModel : HomeVM

    private lateinit var binding: FragmentHomeBinding

//    EXEMPLE GET ARGUMENTS
//    private val args: HomeFragmentArgs by navArgs()


    override fun layoutRes() = R.layout.fragment_home

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

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