package com.example.schoolactivities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolactivities.model.LoginRequest
import com.example.schoolactivities.model.LoginResponse
import com.example.schoolactivities.model.RegistrationRequest
import com.example.schoolactivities.model.RegistrationResponse
import com.example.schoolactivities.userRepository.UserRepository
import kotlinx.coroutines.launch



class UserViewModel: ViewModel() {

    val registrationLiveData= MutableLiveData<RegistrationResponse>()
    val regError=MutableLiveData<String>()
    val userRepository=UserRepository()
    val loginLiveData=MutableLiveData<LoginResponse>()
    val logError=MutableLiveData<String>()
    fun registerUser(registrationRequest: RegistrationRequest){
        viewModelScope.launch {
            val response=userRepository.registerStudent(registrationRequest)
            if (response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                regError.postValue(response.errorBody()?.toString())
            }
        }
    }
    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val respond=userRepository.loginStudent(loginRequest)
            if (respond.isSuccessful){
                loginLiveData.postValue(respond.body())
            }
            else{
                logError.postValue(respond.errorBody()?.toString())

            }

        }

    }

}
