package Adapters

import Fragments.CategoriesListFragment
import Fragments.SingleProductFragment
import Model.ProductsModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimipooria.storeapp.R
import com.squareup.picasso.Picasso

class CategoriesAdapter (var context: Context, categoriesData: ArrayList<String>) :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {
    var categoriesData: List<String>

    init {
        this.categoriesData = categoriesData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtCategory.setText(categoriesData[position])
    }

    override fun getItemCount(): Int {
        return categoriesData.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var txtCategory : TextView

        init {

            txtCategory = itemView.findViewById(R.id.txt_CategoriesItem_Category)


            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {

            val manager = (itemView.context as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val categoriesListFragment = CategoriesListFragment()
            val bundle = Bundle()
            bundle.putString("CategoryName",categoriesData[adapterPosition])
            categoriesListFragment.setArguments(bundle)
            transaction.replace(R.id.fl_categorize,categoriesListFragment)
            transaction.addToBackStack(null)
            transaction.commit()


        }
    }
}