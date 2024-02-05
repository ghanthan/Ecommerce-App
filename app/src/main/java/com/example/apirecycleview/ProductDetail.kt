package com.example.apirecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.net.toUri
import com.squareup.picasso.Picasso

class ProductDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productImage :ImageView = findViewById(R.id.productImageView)
        val productTitle : TextView = findViewById(R.id.productTitleView)
        val productPrice : TextView = findViewById(R.id.priceTextView)

        Picasso.get().load(intent.getStringExtra("productImage")?.toUri()).into(productImage)
        productTitle.text = intent.getStringExtra("productTile").toString()
        productPrice.text = intent.getStringExtra("productPrice").toString()



    }
}