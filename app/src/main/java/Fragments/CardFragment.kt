package Fragments

import Adapters.CartProductsAdapter
import Api.ApiInterface
import Api.RetrofitClient
import Model.ProductsModel
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimipooria.storeapp.MyCartDatabase
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
 * Use the [CardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var list: ArrayList<ProductsModel> = ArrayList()
    lateinit var apiInterface: ApiInterface
    lateinit var recyclerView: RecyclerView
    lateinit var cartProductsAdapter: CartProductsAdapter
    var myCartDatabase : MyCartDatabase? = null
    lateinit var productsId : ArrayList<Int>

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
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardView = view.findViewById<CardView>(R.id.cv_FragmentCard)

        val retrofit = RetrofitClient.getInstance()
        apiInterface = retrofit.create(ApiInterface::class.java)
        recyclerView = view.findViewById(R.id.rv_CardFragment)
        recyclerView.layoutManager = LinearLayoutManager(context)

        productsId = ArrayList<Int>()
        myCartDatabase = MyCartDatabase(view.context)
        val cursor: Cursor = myCartDatabase!!.getInfos
        if(myCartDatabase!=null) {
            while (cursor.moveToNext()) {
                if (!cursor.isAfterLast) {
                    productsId.remove(cursor.getInt(1))
                    productsId.add(cursor.getInt(1))
                    apiInterface.productsList().enqueue(object : Callback<List<ProductsModel>>{
                        override fun onResponse(
                            call: Call<List<ProductsModel>>,
                            response: Response<List<ProductsModel>>
                        ) {
                            list.clear()
                            for(i in response.body()!!){
                                for (singleId in productsId) {
                                    if (i.id.equals(singleId)) {
                                        list.add(i)
                                    }
                                }
                            }
                            cartProductsAdapter = CartProductsAdapter(context!!,list)
                            recyclerView.adapter = cartProductsAdapter

                        }

                        override fun onFailure(call: Call<List<ProductsModel>>, t: Throwable) {
                            Toast.makeText(context,"Error : "+t.message,Toast.LENGTH_SHORT).show()
                            Log.e("Log", "onFailure: "+t.message)
                        }

                    } )
                }
            }
        }

        cardView.setOnClickListener{
            val manager = (context as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val cartOtherShopesFragment = CartOtherShopesFragment()
            transaction.replace(R.id.fl_Card,cartOtherShopesFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

}