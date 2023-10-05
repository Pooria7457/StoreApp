package Adapters


import Model.ProductsModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        holder.txtCategory.setText("Category : "+productsData[position].category)
        holder.txtRate.setText("Rate : "+productsData[position].rating.rate.toString())
        holder.txtCount.setText("Count : "+productsData[position].rating.count.toString())
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
        var txtCategory : TextView
        var txtRate : TextView
        var txtCount : TextView
        var txtDesc : TextView
        var imgProduct : ImageView


        init {

            txtTitle = itemView.findViewById(R.id.txt_productsItem_Title)
            txtPrice = itemView.findViewById(R.id.txt_productsItem_Price)
            txtCategory = itemView.findViewById(R.id.txt_productsItem_Category)
            txtRate = itemView.findViewById(R.id.txt_productsItem_Rate)
            txtCount = itemView.findViewById(R.id.txt_productsItem_Count)
            txtDesc = itemView.findViewById(R.id.txt_productsItem_Description)
            imgProduct = itemView.findViewById(R.id.img_productsItem)

            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {

        }
    }
}