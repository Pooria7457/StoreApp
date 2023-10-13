package Fragments

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.ebrahimipooria.storeapp.MyProfileDatabase
import com.ebrahimipooria.storeapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
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
        myProfileDatabase = MyProfileDatabase(container!!.context)
        val cursor: Cursor? = myProfileDatabase?.getInfos
        if(cursor?.moveToFirst() == true){
            val manager = (context as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val showProfileFragment = ShowProfileFragment()
            transaction.replace(R.id.fl_Profile,showProfileFragment)
            transaction.commit()
        }
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("CommitTransaction")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edtName = view.findViewById<EditText>(R.id.edt_Profile_Name)
        val edtUserName = view.findViewById<EditText>(R.id.edt_Profile_UserName)
        val edtEmail = view.findViewById<EditText>(R.id.edt_Profile_Email)
        val edtPhone = view.findViewById<EditText>(R.id.edt_Profile_PhoneNumber)
        val btnSave = view.findViewById<Button>(R.id.btn_Profile_Save)

        myProfileDatabase = MyProfileDatabase(view.context)

        btnSave.setOnClickListener{

            val name = edtName.text
            val userName = edtUserName.text
            val email = edtEmail.text
            val phone = edtPhone.text

            if(name.toString().isEmpty()||userName.toString().isEmpty()||
                email.toString().isEmpty()||phone.toString().isEmpty()){
                Toast.makeText(view.context,"Please Enter All Parameter",Toast.LENGTH_SHORT).show()
            }else {
                myProfileDatabase!!.addInfo(
                    name.toString(), userName.toString(), email.toString(), phone.toString()
                )
                val manager = (context as FragmentActivity).supportFragmentManager
                val transaction: FragmentTransaction = manager.beginTransaction()
                val showProfileFragment = ShowProfileFragment()
                transaction.replace(R.id.fl_Profile, showProfileFragment)
                transaction.commit()
            }
        }

    }

}