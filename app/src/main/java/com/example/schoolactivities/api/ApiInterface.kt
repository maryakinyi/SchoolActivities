package com.example.schoolactivities.api

import com.example.schoolactivities.model.LoginRequest
import com.example.schoolactivities.model.LoginResponse
import com.example.schoolactivities.model.RegistrationRequest
import com.example.schoolactivities.model.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest):Call<RegistrationResponse>
    fun loginStudent(@Body loginRequest: LoginRequest):Call<LoginResponse>


}