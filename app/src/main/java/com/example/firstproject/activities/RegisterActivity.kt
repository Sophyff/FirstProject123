package com.example.firstproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aapolis.apolisapp.isValidMobileNumber
import com.aapolis.apolisapp.isValidPassword
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.data.Constants
import com.example.firstproject.databinding.ActivityRegisterBinding
import org.json.JSONObject
import java.net.URLEncoder

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)
        binding.btnRegister.setOnClickListener {
            register()
        }

        binding.tvHasAccount.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
    }

    private fun register() {

        if(!isValidForm()) {
            return
        }

        val fullName = URLEncoder.encode(binding.etFullName.text.toString(), "UTF-8")
        val mobileNo = URLEncoder.encode(binding.etMobileNo.text.toString(), "UTF-8")
        val email = URLEncoder.encode(binding.etEmail.text.toString(), "UTF-8")
        val password = URLEncoder.encode(binding.etPassword.text.toString(), "UTF-8")
        val url = "${Constants.BASE_URL}User/register"

        val requestData: JSONObject = JSONObject()
        requestData.put("full_name",fullName)
        requestData.put("mobile_no",mobileNo)
        requestData.put("email_id",email)
        requestData.put("password",password)

        val request= JsonObjectRequest(
            Request.Method.POST,
            url,
            requestData,
            {   output: JSONObject ->
                val status=output.getInt("status")
                if(status==0){
                    Toast.makeText(baseContext,"Register successfully",Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, LoginActivity::class.java))
                    finish()
                }else{
                    val message=output.getString("message")
                    Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
                }

            },{
                    error: VolleyError ->
                error.printStackTrace()
                Toast.makeText(baseContext,"$error",Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)

    }

    private fun isValidForm(): Boolean {
        var isValid = true

        if(binding.etFullName.text.toString().isEmpty()) {
            binding.etFullName.error = "Please enter name"
            isValid = false
        }

        if(binding.etMobileNo.text.toString().isEmpty()) {
            binding.etMobileNo.error = "Please enter mobile number"
            isValid = false
        } else if(!isValidMobileNumber(binding.etMobileNo.text.toString())) {
            binding.etMobileNo.error = "Please enter 10 digit mobile number"
            isValid = false
        }

        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        if(!isValidPassword(password)) {
            binding.etPassword.error = "Wrong password. It should be alphanumeric with min length 6."
            isValid = false
        } else if(!password.equals(confirmPassword)) {
            binding.etConfirmPassword.error = "Password and confirm password should be same."
            isValid = false
        }


        return isValid
    }
}