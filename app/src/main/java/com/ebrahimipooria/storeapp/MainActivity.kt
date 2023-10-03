package com.ebrahimipooria.storeapp

import Api.ApiInterface
import Api.RetrofitClient
import Model.LoginModel
import Model.ResponseLoginModel
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtUserName = findViewById<EditText>(R.id.edt_Main_UserName)
        val edtPassword = findViewById<EditText>(R.id.edt_Main_Password)
        val btnLogin = findViewById<Button>(R.id.btn_Main_Login)

        val userNameText = edtUserName.text
        val passwordText = edtPassword.text

        val loginModel = LoginModel(userNameText.toString(),passwordText.toString())

        val retrofit = RetrofitClient.getInstance()
        val apiInterface : ApiInterface = retrofit.create(ApiInterface::class.java)

        btnLogin.setOnClickListener {

            if(userNameText.toString().isEmpty()||passwordText.toString().isEmpty()){
                Toast.makeText(this@MainActivity,"Enter userName and password",Toast.LENGTH_SHORT).show()
            }else{

                apiInterface.userLogin(loginModel).enqueue(object : Callback<ResponseLoginModel>{
                    override fun onResponse(
                        call: Call<ResponseLoginModel>,
                        response: Response<ResponseLoginModel>
                    ) {
                        val tokenId = response.body()!!.token
                        Toast.makeText(this@MainActivity,tokenId,Toast.LENGTH_SHORT).show()
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