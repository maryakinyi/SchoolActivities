package com.example.schoolactivities.api

import com.example.schoolactivities.model.LoginRequest
import com.example.schoolactivities.model.LoginResponse
import com.example.schoolactivities.model.RegistrationRequest
import com.example.schoolactivities.model.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/register")
   suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>
   @POST("/student/login")
    fun loginStudent(@Body loginRequest: LoginRequest):Response<LoginResponse>


}