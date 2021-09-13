package com.evyatar.motorolaassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.evyatar.motorolaassignment.MainListener
import com.evyatar.motorolaassignment.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(UsersDataFragment.newInstance(this), "USERS_DATA_FRAGMENT")
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun showUserCountdownToBirthDate() {
        showFragment(UserBirtDateCountDownFragment.newInstance(this), "BIRTH DATE FRAGMENT")
    }
}