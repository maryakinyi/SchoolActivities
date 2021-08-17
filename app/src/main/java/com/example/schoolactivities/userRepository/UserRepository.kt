package com.example.schoolactivities.userRepository

import com.example.schoolactivities.api.ApiClient
import com.example.schoolactivities.api.ApiInterface
import com.example.schoolactivities.model.LoginRequest
import com.example.schoolactivities.model.LoginResponse
import com.example.schoolactivities.model.RegistrationRequest
import com.example.schoolactivities.model.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class UserRepository {
    var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerStudent(registrationRequest: RegistrationRequest):Response<RegistrationResponse> =
       withContext(Dispatchers.IO){
           val resp=retrofit.registerStudent(registrationRequest)
           return@withContext resp
       }

//    suspend fun loginStudent(loginRequest: LoginRequest): Unit =
//        withContext(Dispatchers.IO){
//            var response=retrofit.loginStudent(LoginRequest)
//            return@withContext response
//        }
suspend fun loginStudent(logInRequest: LoginRequest):Response<LoginResponse> =
    withContext(Dispatchers.IO){
        var response = retrofit.loginStudent(logInRequest)
        return@withContext response
    }


        }



