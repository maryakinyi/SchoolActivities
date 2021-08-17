package com.example.schoolactivities.model

import com.google.gson.annotations.SerializedName

data class
LoginRequest(var email:String,
                        @SerializedName("password")var password:String)
