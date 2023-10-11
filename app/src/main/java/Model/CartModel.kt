package Model

import com.google.gson.annotations.SerializedName

data class CartModel(

    @SerializedName("id")
    var id : Int ,

    @SerializedName("userId")
    var userId : Int ,

    @SerializedName("date")
    var date : String ,

    @SerializedName("products")
    var products : ArrayList<CartProductsModel> ,

    @SerializedName("__v")
    var __v : Int

)
