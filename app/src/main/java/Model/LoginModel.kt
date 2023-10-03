package Model

import com.google.gson.annotations.SerializedName

data class LoginModel(

    @SerializedName("username")
    var username : String ,

    @SerializedName("password")
    var password : String

)
