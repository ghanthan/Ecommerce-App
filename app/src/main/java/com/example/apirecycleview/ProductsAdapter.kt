package com.example.apirecycleview

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class ProductsAdapter(val context: Context, val dataList: List<Product>):RecyclerView.Adapter<ProductsAdapter.ViewHolder> (){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val itemView = LayoutInflater.from(context).inflate(R.layout.products_activity,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
      return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = dataList[position]
        Picasso.get().load(currentData.thumbnail.toUri()).into(holder.prouctThumbnail)
        holder.productTitle.text = currentData.title
        holder.productDescription.text = currentData.description
        holder.productPrice.text = "$"+ currentData.price.toString()
        holder.productRating.text = currentData.rating.toString()

        holder.titleLayout.setOnClickListener(View.OnClickListener {

            val intent = Intent(context,ProductDetail::class.java)
            intent.putExtra("productImage",currentData.thumbnail)
            intent.putExtra("productPrice",currentData.price)
            intent.putExtra("productDescription",currentData.description)
            intent.putExtra("productTitle",currentData.title)
            context.startActivity(intent)
        })

    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val productTitle : TextView
        val productDescription : TextView
        val productPrice : TextView
        val productRating : TextView
        val prouctThumbnail : ImageView
        val titleLayout : View

        init {
            productTitle = itemView.findViewById(R.id.tvTitle)
            productDescription = itemView.findViewById(R.id.tvDescription)
            productPrice = itemView.findViewById(R.id.tvPrice)
            productRating = itemView.findViewById(R.id.tvproductRating)
            prouctThumbnail = itemView.findViewById(R.id.productImage)
            titleLayout = itemView.findViewById(R.id.titleLayout)
        }
    }
}