package com.example.schoolactivities

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.core.view.isEmpty
import com.example.schoolactivities.api.ApiClient
import com.example.schoolactivities.api.ApiInterface
import com.example.schoolactivities.databinding.ActivityMainBinding
import com.example.schoolactivities.model.RegistrationRequest
import com.example.schoolactivities.model.RegistrationResponse
import com.example.schoolactivities.ui.StudentLogin
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel:UserViewModel by viewModels()
    lateinit var btnRegister:Button
//    lateinit var etName:EditText
//    lateinit var etEmail: EditText
//    lateinit var spGender:Spinner
//    lateinit var spNationality:Spinner
//    lateinit var etPhone:EditText
//    lateinit var etId:EditText
//    lateinit var etDob:EditText
//    lateinit var etPassword:EditText
//    lateinit var btnRegister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

    spinner()
    clickRegister()
 }
 fun spinner(){

        var nationalities= arrayOf("Kenyan","Ugandan","South Sudanese","Sudanese","Rwandan")
        var nationalitiesAdapter=ArrayAdapter(this,android.R.layout.simple_selectable_list_item,nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter=nationalitiesAdapter
    }
    fun  clickRegister() {
        var error = false
        btnRegister.setOnClickListener {
            var error=false
            var name = binding.etName.toString()
            if (name.isEmpty()) {
                error = true

                binding.etName.setError("Name is required")
            }
//            var dob = binding.etDoB
//            var nationality = binding.spNationality
//            var password = binding.etPassword
//            var phone = binding.etP
            if (name.isEmpty()) {
                error = true

                binding.etName.setError("Name is required")
            }
            var dob = binding.etDoB.toString()
            if (dob.isEmpty()) {
                error = true
                binding.etDoB.setError("Date of birth is required ")
            }
            var nationality = ""
            var password = binding.etPassword.toString()
            if (password.isEmpty()) {
                error = true
                binding.etEmail.setError("Password is required ")
            }
            var phone = binding.etPhone.toString()
            if (phone.isEmpty()) {
                error = true
                binding.etEmail.setError("Phone number is required ")
            }
            var email = binding.etEmail.toString()
            if (email.isEmpty()) {
                error = true
                binding.etEmail.setError("Email is required ")
            }
            var registrationRequest = RegistrationRequest(
               name = name,phoneNumber = phone,email = email,dateOfBirth = dob,nationality = nationality,password = password

            )
//            var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
//            var request=retrofit.registerStudent( registrationRequest)
//            request.enqueue(object : Callback<RegistrationResponse> {
//        }
            userViewModel.registerUser(registrationRequest)
            val intent=Intent(baseContext,StudentLogin::class.java)
            startActivity(intent)
    }
     fun onResponse(
                    call: Call<RegistrationResponse>,
                    response: Response<RegistrationResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(baseContext,error.toString(),Toast.LENGTH_LONG).show()
                    }
                    else{
                        try {
                            val error=JSONObject(response.errorBody()!!.toString())
                        }
                        catch (e:Exception){
                            Toast.makeText(baseContext,e.message,Toast.LENGTH_LONG).show()
                        }
                    }
                }

                 fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
                }

            }
}
data class ApiError(var errors:List<String>)