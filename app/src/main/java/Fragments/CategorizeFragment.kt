package Fragments

import Adapters.CategoriesAdapter
import Adapters.ProductsAdapter
import Api.ApiInterface
import Api.RetrofitClient
import Model.ProductsModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
 * Use the [CategorizeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategorizeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var list: ArrayList<String> = ArrayList()
    lateinit var apiInterface: ApiInterface
    lateinit var recyclerView: RecyclerView
    lateinit var categoriesAdapter: CategoriesAdapter

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
        return inflater.inflate(R.layout.fragment_categorize, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategorizeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategorizeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClient.getInstance()
        apiInterface = retrofit.create(ApiInterface::class.java)
        recyclerView = view.findViewById(R.id.rv_CategoriesFragment)
        recyclerView.layoutManager = LinearLayoutManager(context)

        apiInterface.categories().enqueue(object : Callback<ArrayList<String>>{
            override fun onResponse(
                call: Call<ArrayList<String>>,
                response: Response<ArrayList<String>>
            ) {
                for(i in response.body()!!){
                    list.add(i)
                }
                categoriesAdapter = CategoriesAdapter(context!!,list)
                recyclerView.adapter = categoriesAdapter
            }

            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                Toast.makeText(context,"Error : "+t.message, Toast.LENGTH_SHORT).show()
                Log.e("Log", "onFailure: "+t.message)
            }

        })

    }

}