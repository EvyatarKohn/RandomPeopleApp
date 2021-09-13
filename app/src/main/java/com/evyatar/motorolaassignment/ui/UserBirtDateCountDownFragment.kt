package com.evyatar.motorolaassignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evyatar.motorolaassignment.MainListener
import com.evyatar.motorolaassignment.R

class UserBirtDateCountDownFragment: Fragment() {
    private lateinit var mMainListener: MainListener

    companion object {
        fun newInstance(mainListener: MainListener) =
            UserBirtDateCountDownFragment().apply {
                mMainListener = mainListener
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.user_data_layout, container, false)

        return v
    }
}