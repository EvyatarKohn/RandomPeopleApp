package com.evyatar.motorolaassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.evyatar.motorolaassignment.MainListener
import com.evyatar.motorolaassignment.R
import com.evyatar.motorolaassignment.model.usersmodel.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainListener {

    private var mFirsTimeBack = true
    var mBackPressed = false

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

    override fun proceedToBDFragment(result: Result) {
        showFragment(UserBirthDateCountDownFragment.newInstance(result, this), "BIRTH DATE FRAGMENT")
    }

    override fun proceedToEmailApp(email: String) {
        /* Create the Intent */
        val emailIntent = Intent(Intent.ACTION_SEND)

        /* Fill it with Data */
        emailIntent.type = "plain/text"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))

        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(emailIntent, "Send"))
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            mBackPressed = true
            super.onBackPressed()
        } else {
            if (mFirsTimeBack) {
                Toast.makeText(applicationContext, "press again to exit", Toast.LENGTH_LONG).show()
                mFirsTimeBack = false
            } else {
                finish()
            }
        }
    }
}