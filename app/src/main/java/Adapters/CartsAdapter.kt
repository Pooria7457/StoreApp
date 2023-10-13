package Adapters


import Fragments.SingleCartFragment
import Model.CartModel
import Model.CartProductsModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimipooria.storeapp.R

class CartsAdapter (var context: Context, cartsData: ArrayList<CartModel>,
                    cartsProductData: ArrayList<CartProductsModel>) :
    RecyclerView.Adapter<CartsAdapter.MyViewHolder>() {
    var cartsData: List<CartModel>
    var cartsProductData: ArrayList<CartProductsModel>

    init {
        this.cartsData = cartsData
        this.cartsProductData = cartsProductData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.carts_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.txtUserId.setText("User Id : "+cartsData[position].userId.toString())
       holder.txtDate.setText("Date : "+cartsData[position].date)
       holder.txtProductId.setText("Product Id : "+cartsProductData[position].productId.toString())
       holder.txtQuantity.setText("Quantity : "+cartsProductData[position].quantity.toString())
    }

    override fun getItemCount(): Int {
        return cartsData.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var txtUserId : TextView
        var txtDate : TextView
        var txtProductId : TextView
        var txtQuantity : TextView


        init {

            txtUserId = itemView.findViewById(R.id.txt_CartItems_UserId)
            txtDate  = itemView.findViewById(R.id.txt_CartItems_Date)
            txtProductId = itemView.findViewById(R.id.txt_CartItems_ProductId)
            txtQuantity = itemView.findViewById(R.id.txt_CartItems_Quantity)

            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            val manager = (itemView.context as FragmentActivity).supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            val singleCartFragment = SingleCartFragment()
            val bundle = Bundle()
            bundle.putInt("id",cartsData[adapterPosition].id)
            bundle.putInt("userId",cartsData[adapterPosition].userId)
            bundle.putString("date",cartsData[adapterPosition].date)
            bundle.putInt("productId",cartsProductData[adapterPosition].productId)
            bundle.putInt("quantity",cartsProductData[adapterPosition].quantity)
            singleCartFragment.setArguments(bundle)
            transaction.replace(R.id.fl_OtherShop,singleCartFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}