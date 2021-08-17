package com.example.schoolactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.schoolactivities.api.ApiClient
import com.example.schoolactivities.api.ApiInterface
import com.example.schoolactivities.model.RegistrationRequest
import com.example.schoolactivities.model.RegistrationResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var etName:EditText
    lateinit var etEmail: EditText
    lateinit var spGender:Spinner
    lateinit var spNationality:Spinner
    lateinit var etPhone:EditText
    lateinit var etId:EditText
    lateinit var etDob:EditText
    lateinit var etPassword:EditText
    lateinit var btnRegister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castView()
        clickRegister()
    }
    fun castView(){
        etName=findViewById(R.id.etName)
        etEmail=findViewById(R.id.etEmail)
        spGender=findViewById(R.id.spGender)
        spNationality=findViewById(R.id.spNationality)
        etId=findViewById(R.id.etId)
        etPhone=findViewById(R.id.etPhone)
        etDob=findViewById(R.id.etDoB)
        etPassword=findViewById(R.id.etPassword)

        var nationalities= arrayOf("Kenyan","Ugandan","South Sudanese","Sudanese","Rwandan")
        var nationalitiesAdapter=ArrayAdapter(this,android.R.layout.simple_selectable_list_item,nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNationality.adapter=nationalitiesAdapter
    }
    fun  clickRegister(){
        var error=false
        btnRegister.setOnClickListener {
            var name=etName.text.toString()
            if (name.isEmpty()){
                error=true
                etName.setError("Name is required")
            }
            var dob=etDob.text.toString()
            var nationality=spNationality.selectedItem.toString()
            var password=etPassword.text.toString()
            var phone=etPhone.text.toString()
            var email=etEmail.text.toString()
            if (email.isEmpty()){
                error=true
                etEmail.setError("Email is required ")
            }
            var registrationRequest= RegistrationRequest(name=name,phoneNumber = phone,email=email,nationality = nationality.uppercase(),dateOfBirth = dob,password = password)

            var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
            var request=retrofit.registerStudent( registrationRequest)
            request.enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(
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

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}
data class ApiError(var errors:List<String>)