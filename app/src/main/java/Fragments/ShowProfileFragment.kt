package Fragments

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ebrahimipooria.storeapp.MyProfileDatabase
import com.ebrahimipooria.storeapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var myProfileDatabase: MyProfileDatabase? = null

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
        return inflater.inflate(R.layout.fragment_show_profile, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShowProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShowProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtUserName = view.findViewById<TextView>(R.id.txt_ShowProfile_UserName)
        val txtName = view.findViewById<TextView>(R.id.txt_ShowProfile_Name)
        val txtEmail = view.findViewById<TextView>(R.id.txt_ShowProfile_Email)
        val txtPhone = view.findViewById<TextView>(R.id.txt_ShowProfile_Phone)

        myProfileDatabase = MyProfileDatabase(view.context)
        val cursor: Cursor = myProfileDatabase!!.getInfos
        if(myProfileDatabase!=null){
            while (cursor.moveToNext()){
                if(!cursor.isAfterLast){
                    txtName.setText("Name : "+cursor.getString(1))
                    txtUserName.setText("UserName : "+cursor.getString(2))
                    txtEmail.setText("Email : "+cursor.getString(3))
                    txtPhone.setText("Phone : "+cursor.getString(4))
                }
            }
        }

    }

}