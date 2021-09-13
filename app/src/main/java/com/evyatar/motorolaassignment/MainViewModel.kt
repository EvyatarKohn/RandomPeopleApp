package com.evyatar.motorolaassignment

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.evyatar.motorolaassignment.model.usersmodel.UsersData
import com.evyatar.motorolaassignment.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepo,
    application: Application
) : AndroidViewModel(application) {

    private var mUserLiveData = MutableLiveData<UsersData>()
    val userRepo: LiveData<UsersData>
        get() = mUserLiveData


    fun getUserData() = viewModelScope.launch {
        repository.getUserData().let { response ->
            if (response.isSuccessful) {
                mUserLiveData.postValue(response.body())
            } else {
                Toast.makeText(
                    getApplication<Application>().applicationContext,
                    getApplication<Application>().applicationContext.resources.getString(R.string.user_fetch_request_failure),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}