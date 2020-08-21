package com.sebag.florent.presenter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.sebag.florent.presenter.R
import kotlinx.android.synthetic.main.activity_base.*

class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        if (isUserConnected())
            setStartDestToHome()
    }

    private fun isUserConnected() : Boolean {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        return prefs.getBoolean("isConnected", false)
    }

    private fun setStartDestToHome() {
        val navHostFragment = nav_host_fragment as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation_graph)

        graph.startDestination = R.id.homeFragment
        navHostFragment.navController.graph = graph
    }
}