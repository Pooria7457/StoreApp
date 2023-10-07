package Fragments

import Api.ApiInterface
import Api.RetrofitClient
import Model.ProductsModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ebrahimipooria.storeapp.R
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SingleProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingleProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_product, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SingleProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SingleProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtTitle = view.findViewById<TextView>(R.id.txt_Single_title)
        val txtPrice = view.findViewById<TextView>(R.id.txt_Single_Price)
        val txtCategory = view.findViewById<TextView>(R.id.txt_Single_Category)
        val txtRate = view.findViewById<TextView>(R.id.txt_Single_Rate)
        val txtCount = view.findViewById<TextView>(R.id.txt_Single_Count)
        val txtDesc = view.findViewById<TextView>(R.id.txt_Single_Description)
        val imgSingle = view.findViewById<ImageView>(R.id.img_Single)

        val id : Int? = getArguments()?.getInt("id")
        val title : String? = getArguments()?.getString("title")
        val price : Double? = getArguments()?.getDouble("price")
        val category : String? = getArguments()?.getString("category")
        val rate : Double? = getArguments()?.getDouble("rate")
        val count : Int? = getArguments()?.getInt("count")
        val desc : String? = getArguments()?.getString("desc")
        val image : String? = getArguments()?.getString("image")

        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.getSingleProduct(id!!).enqueue(object : Callback<ProductsModel>{
            override fun onResponse(call: Call<ProductsModel>, response: Response<ProductsModel>) {
                Picasso.get().load(image).into(imgSingle)
                txtTitle.setText("Title : "+title)
                txtPrice.setText("Price : "+price.toString()+" $ ")
                txtCategory.setText("Category : "+category)
                txtRate.setText("Rate : "+rate.toString())
                txtCount.setText("Count : "+count.toString())
                txtDesc.setText("Description : "+desc)
            }

            override fun onFailure(call: Call<ProductsModel>, t: Throwable) {
                Toast.makeText(context,"Error : "+t.message, Toast.LENGTH_SHORT).show()
                Log.e("Log", "onFailure: "+t.message)
            }

        })

    }

}