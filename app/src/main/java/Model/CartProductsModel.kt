package Model

import com.google.gson.annotations.SerializedName

class CartProductsModel(

    @SerializedName("productId")
    var productId : Int ,

    @SerializedName("quantity")
    var quantity : Int

)
