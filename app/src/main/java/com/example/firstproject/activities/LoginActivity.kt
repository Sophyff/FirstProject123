package com.example.firstproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.firstproject.data.Constants
import com.example.firstproject.data.remote.LoginResponse
import com.example.firstproject.data.remote.User
import com.example.firstproject.databinding.ActivityLoginBinding
import com.google.gson.Gson
import org.json.JSONObject
import java.net.URLEncoder


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)
        binding.tvDontHaveAccount.setOnClickListener {
            startActivity(Intent(baseContext, RegisterActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {

        val email = URLEncoder.encode(binding.etEmail.text.toString(), "UTF-8")
        val password = URLEncoder.encode(binding.etPassword.text.toString(), "UTF-8")
        val url = "${Constants.BASE_URL}User/auth"

        val requestData: JSONObject = JSONObject()
        requestData.put("email_id",email)
        requestData.put("password",password)

        val request= JsonObjectRequest(
            Request.Method.POST,
            url,
            requestData,
            {
                binding.pbProcessing.visibility = View.GONE
                val status=it.getInt("status")

                val gson= Gson()
                val response: LoginResponse =gson.fromJson(it.toString(), LoginResponse::class.java)
                if(status==0){
                    val user=response.user
                    saveUserInfo(user)
                }else{
                    val message=it.getString("message")
                    Toast.makeText(baseContext,message, Toast.LENGTH_LONG).show()
                }

            },{
                    error: VolleyError ->
                error.printStackTrace()
                Toast.makeText(baseContext,"$error", Toast.LENGTH_LONG).show()
            }
        )


        queue.add(request)
        binding.pbProcessing.visibility = View.VISIBLE
    }

    private fun saveUserInfo(user: User) {

        val pref = getSharedPreferences("User", MODE_PRIVATE)
        val editor = pref.edit()

        editor.putString("name", user.full_name)
        editor.putString("mobile_no", user.mobile_no)
        editor.putString("user_id",user.user_id)
        editor.commit()

        startActivity(Intent(baseContext, DrawNavActivity::class.java))
        finish()
    }
}