package com.evyatar.motorolaassignment

import com.evyatar.motorolaassignment.model.usersmodel.Result

interface MainListener {
    fun proceedToBDFragment(result: Result)
    fun proceedToEmailApp(email: String)
}
