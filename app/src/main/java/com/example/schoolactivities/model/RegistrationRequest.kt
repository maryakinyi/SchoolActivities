package com.example.schoolactivities.model

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    var name:String,
    @SerializedName("phone_number")var phoneNumber:String,
    var email:String, @SerializedName("data_of_birth")var dateOfBirth:String,
    var nationality:String, var password:String
)
