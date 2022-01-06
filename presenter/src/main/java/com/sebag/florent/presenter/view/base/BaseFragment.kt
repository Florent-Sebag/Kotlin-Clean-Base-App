package com.sebag.florent.presenter.view.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sebag.florent.presenter.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.loading_dialog.view.*

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    @LayoutRes
    protected abstract fun layoutRes() : Int

    protected lateinit var binding : T

    private lateinit var loadingDialog : AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            layoutRes(),
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    protected fun showLoadingDialog(msg : String) {
        val v = layoutInflater.inflate(R.layout.loading_dialog, null)
        v.loading_msg.text = msg
        loadingDialog = AlertDialog.Builder(this.requireContext())
            .setView(v)
            .show()
    }

    protected fun hideLoadingDialog() {
        if (loadingDialog.isShowing)
            loadingDialog.dismiss()
    }

    protected fun showToastMessage(msg: String) {
        this.view?.let {
            Snackbar.make(it, msg, Snackbar.LENGTH_SHORT).show()
        }
    }

    protected fun isBadInput(editText: TextInputEditText,
                              layout: TextInputLayout,
                              onErrorMsg : String,
                              checkFun: (input: String) -> Boolean)
    : Boolean {

        var res = false
        if (!checkFun(editText.text.toString()))
            res = setErrorInput(onErrorMsg, layout)
        else
            unsetErrorInput(layout)

        return (res)
    }

    private fun setErrorInput(msg: String, layout: TextInputLayout) : Boolean {
        layout.error = msg
        layout.isErrorEnabled = true
        return (true)
    }

    private fun unsetErrorInput(layout: TextInputLayout) {
        layout.isErrorEnabled = false
    }
}