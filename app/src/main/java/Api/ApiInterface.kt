package Api

import Model.LoginModel
import Model.ResponseLoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/auth/login")
    fun userLogin(
        @Body loginModel: LoginModel
    ): Call<ResponseLoginModel>

}