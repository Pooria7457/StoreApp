package Api

import Model.LoginModel
import Model.ProductsModel
import Model.ResponseLoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("/auth/login")
    fun userLogin(
        @Body loginModel: LoginModel
    ): Call<ResponseLoginModel>

    @GET("/products")
    fun productsList() :Call <List<ProductsModel>>


    @GET("/products/{id}")
    fun getSingleProduct(@Path("id") id: Int): Call<ProductsModel>


}