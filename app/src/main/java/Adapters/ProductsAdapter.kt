package Adapters


import Fragments.SingleProductFragment
import Model.ProductsModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimipooria.storeapp.R
import com.squareup.picasso.Picasso


class ProductsAdapter (var context: Context, productsData: ArrayList<ProductsModel>) :
    RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {
    var productsData: List<ProductsModel>

    init {
        this.productsData = productsData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.products_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTitle.setText("Title : "+productsData[position].title)
        holder.txtPrice.setText("Price : "+productsData[position].price.toString()+" $ ")
        holder.txtDesc.setText("Description : "+productsData[position].description)
        Picasso.get().load(productsData[position].image).into(holder.imgProduct)
    }

    override fun getItemCount(): Int {
        return productsData.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var txtTitle : TextView
        var txtPrice : TextView
        var txtDesc : TextView
        var imgProduct : ImageView


        init {

            txtTitle = itemView.findViewById(R.id.txt_productsItem_Title)
            txtPrice = itemView.findViewById(R.id.txt_productsItem_Price)
            txtDesc = itemView.findViewById(R.id.txt_productsItem_Description)
            imgProduct = itemView.findViewById(R.id.img_productsItem)

            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {

            val manager = (itemView.context as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val singleProductFragment = SingleProductFragment()
            val bundle = Bundle()
            bundle.putInt("id", productsData[adapterPosition].id)
            bundle.putString("title", productsData[adapterPosition].title)
            bundle.putDouble("price", productsData[adapterPosition].price)
            bundle.putString("category", productsData[adapterPosition].category)
            bundle.putDouble("rate", productsData[adapterPosition].rating.rate)
            bundle.putInt("count", productsData[adapterPosition].rating.count)
            bundle.putString("desc", productsData[adapterPosition].description)
            bundle.putString("image", productsData[adapterPosition].image)
            singleProductFragment.setArguments(bundle)
            transaction.replace(R.id.fl_Home,singleProductFragment)
            transaction.addToBackStack(null)
            transaction.commit()


        }
    }
}