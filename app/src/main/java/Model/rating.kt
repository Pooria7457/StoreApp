package Model

import com.google.gson.annotations.SerializedName

data class rating(

    @SerializedName("rate")
    var rate : Double ,

    @SerializedName("count")
    var count : Int

)
