package com.example.schoolactivities.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.schoolactivities.R
import com.example.schoolactivities.api.ApiClient
import com.example.schoolactivities.api.ApiInterface
import com.example.schoolactivities.model.LoginRequest
import com.example.schoolactivities.model.LoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class StudentLogin : AppCompatActivity() {
    lateinit var tvEmail:EditText
    lateinit var tvPassword:EditText
    lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)
        display()
    }
    fun display(){
        tvEmail=findViewById(R.id.etEmailLogin)
        tvPassword=findViewById(R.id.etLoginPassword)
        btnLogin=findViewById(R.id.btLogin)

    }
    fun login(){
        btnLogin.setOnClickListener {
            var email=tvEmail.text.toString()
            if (email.isEmpty()){
                var error=true
                tvEmail.setError("Email is required")
            }
            var password=tvPassword.text.toString()
            if (password.isEmpty()){
                var error=true
                tvPassword.setError("Enter correct password")
                var loginRequest=LoginRequest(email = email,password = password)
                var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
                var request=retrofit.loginStudent( loginRequest)
                request.enqueue(object : Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(baseContext,error.toString(), Toast.LENGTH_LONG).show()
                        }
                        else{
                            try {
                                val error=JSONObject(response.errorBody()!!.toString())
                            }
                            catch(e:Exception){
                                Toast.makeText(baseContext,e.message,Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
                    }

                })
//
          }
       }
   }

}