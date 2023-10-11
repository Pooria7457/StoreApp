package Fragments

import Api.ApiInterface
import Api.RetrofitClient
import Model.CartModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.ebrahimipooria.storeapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SingleCartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SingleCartFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_single_cart, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SingleCartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SingleCartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtUserId = view.findViewById<TextView>(R.id.txt_SingleCart_UserId)
        val txtDate = view.findViewById<TextView>(R.id.txt_SingleCart_Date)
        val txtProductId = view.findViewById<TextView>(R.id.txt_SingleCart_ProductId)
        val txtQuantity = view.findViewById<TextView>(R.id.txt_SingleCart_Quantity)

        val id : Int? = getArguments()?.getInt("id")
        val userId : Int? = getArguments()?.getInt("userId")
        val date : String? = getArguments()?.getString("date")
        val productId : Int? = getArguments()?.getInt("productId")
        val quantity : Int? = getArguments()?.getInt("quantity")

        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.getSingleCart(id!!).enqueue(object : Callback<CartModel>{
            override fun onResponse(call: Call<CartModel>, response: Response<CartModel>) {
                txtUserId.setText("User Id : "+userId.toString())
                txtDate.setText("Date : "+date)
                txtProductId.setText("Product Id : "+productId.toString())
                txtQuantity.setText("Quantity : "+quantity.toString())
            }

            override fun onFailure(call: Call<CartModel>, t: Throwable) {
                Toast.makeText(context,"Error : "+t.message, Toast.LENGTH_SHORT).show()
                Log.e("Log", "onFailure: "+t.message)
            }

        })

    }

}