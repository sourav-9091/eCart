package com.example.ecart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecart.databinding.ActivityProductDetailBinding
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getParcelableExtra<Product>("product")

        product?.let {
            binding.productTitle.text = it.title
            binding.productDescription.text = it.description
            binding.productPrice.text = "Price: $${it.price}"
            binding.productRating.text = "Rating: ${it.rating}"
            binding.productStock.text = "Stock: ${it.stock}"
            binding.productCategory.text = "Category: ${it.category}"
            binding.productBrand.text = "Brand: ${it.brand}"
            binding.productSku.text = "SKU: ${it.sku}"
            binding.productWarranty.text = "Warranty: ${it.warrantyInformation}"
            binding.productShipping.text = "Shipping: ${it.shippingInformation}"
            binding.productAvailability.text = "Availability: ${it.availabilityStatus}"
            binding.productReturnPolicy.text = "Return Policy: ${it.returnPolicy}"
            Picasso.get().load(it.thumbnail).into(binding.productImage)
        }
    }
}
