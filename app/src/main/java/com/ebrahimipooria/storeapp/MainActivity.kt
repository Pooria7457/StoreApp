package com.ebrahimipooria.storeapp

import Api.ApiInterface
import Api.RetrofitClient
import Model.LoginModel
import Model.ResponseLoginModel
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ebrahimipooria.storeapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val sharedToken = sharedPreferences.getString("token", "")
        if (sharedToken != "") {
            val intent = Intent(this@MainActivity,HomeActivity::class.java)
            startActivity(intent)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val userNameText = binding.edtMainUserName.text
        val passwordText = binding.edtMainPassword.text


        val retrofit = RetrofitClient.getInstance()
        val apiInterface : ApiInterface = retrofit.create(ApiInterface::class.java)

        binding.btnMainLogin.setOnClickListener {
            val loginModel = LoginModel(userNameText.toString(),passwordText.toString())

            if(userNameText.toString().isEmpty()||passwordText.toString().isEmpty()){
                Toast.makeText(this@MainActivity,"Enter userName and password",Toast.LENGTH_SHORT).show()
            }else{
                Log.e("KIAA", "onCreate: "+Gson().toJson(loginModel) )
                apiInterface.userLogin(loginModel).enqueue(object : Callback<ResponseLoginModel>{
                    override fun onResponse(
                        call: Call<ResponseLoginModel>,
                        response: Response<ResponseLoginModel>
                    ) {
                        if(response.isSuccessful){
                            val tokenId = response.body()!!.token
                            sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("token", tokenId)
                            editor.apply()
                            val intent = Intent(this@MainActivity,HomeActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@MainActivity,"Username Or Password Is Wrong",Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailure(call: Call<ResponseLoginModel>, t: Throwable) {
                        Toast.makeText(this@MainActivity,"Error : "+t.message,Toast.LENGTH_SHORT).show()
                        Log.e("Log","Error : "+t.message)
                    }

                } )

            }

        }

    }
}