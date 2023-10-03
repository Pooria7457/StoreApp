package Model

import com.google.gson.annotations.SerializedName

data class ResponseLoginModel(

    @SerializedName("token")
    var token : String

)
