package com.evyatar.motorolaassignment.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieAnimationView
import com.evyatar.motorolaassignment.MainListener
import com.evyatar.motorolaassignment.MainViewModel
import com.evyatar.motorolaassignment.R
import com.evyatar.motorolaassignment.model.usersmodel.UsersData
import com.evyatar.motorolaassignment.ui.adapters.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_data_layout.*


@AndroidEntryPoint
class UsersDataFragment : Fragment() {

    private val mViewModel: MainViewModel by viewModels()
    private lateinit var mMainListener: MainListener
    private lateinit var mUserAdapter: UserAdapter
    private lateinit var mLoaderLottie: LottieAnimationView
    private lateinit var mSwipeRefresh: SwipeRefreshLayout
    private lateinit var mInternetLayout: TextView

    companion object {
        fun newInstance(mainListener: MainListener) =
            UsersDataFragment().apply {
                mMainListener = mainListener
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.user_data_layout, container, false)

        mInternetLayout = v.findViewById(R.id.internet_indicator)
        mLoaderLottie = v.findViewById(R.id.loader_lottie)
        mSwipeRefresh = v.findViewById(R.id.refresh_swipe)
        mSwipeRefresh.setOnRefreshListener {
            mInternetLayout.visibility = View.INVISIBLE
            fetchUsers()
            mSwipeRefresh.isRefreshing = false
        }
        if (!(activity as MainActivity).mBackPressed) {
            fetchUsers()
        }

        mViewModel.userRepo.observe(viewLifecycleOwner, Observer { userData ->
            mLoaderLottie.visibility = View.GONE
            val sortedUsersList = userData.results.sortedBy { result ->
                result.dob.age
            }
            mUserAdapter = UserAdapter(sortedUsersList, mMainListener)

            val layoutManager = LinearLayoutManager(activity?.applicationContext)
            user_recycler.layoutManager = layoutManager
            user_recycler.adapter = mUserAdapter
        })

        return v
    }

    private fun fetchUsers() {
        if (isInternetOn()) {
            mInternetLayout.visibility = View.INVISIBLE
            mViewModel.getUserData()
        } else {
            mInternetLayout.visibility = View.VISIBLE
        }
    }

    private fun isInternetOn(): Boolean {
        val mgr =
            activity?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = mgr.activeNetworkInfo

        if (netInfo != null) {
            if (netInfo.isConnected) {
                mInternetLayout.visibility = View.GONE
                return true
            }
            mInternetLayout.visibility = View.VISIBLE
            return false
        }
        mInternetLayout.visibility = View.VISIBLE
        return false
    }
}