package com.example.resdelivery.features.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.resdelivery.R
import com.example.resdelivery.features.login.LoginFragmentDirections
import com.example.resdelivery.util.SessionManagement
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

/*
    we only made this splash screen to detect if the user is logged in or not
    and it already has the same background as the splash layer-list screen
 */
class SplashFragment : Fragment() {

    private val sessionManagement: SessionManagement by inject()
    private val handler = Handler()
    private val runnable = Runnable {
        if (sessionManagement.isLoggedIn()) {
            findNavController().navigate(
                R.id.foodListFragment
            )
        } else {
            findNavController().navigate(
                R.id.loginFragment
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        handler.postDelayed(runnable, 3)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onDestroyView() {
        handler.removeCallbacks(runnable)
        super.onDestroyView()
    }
}