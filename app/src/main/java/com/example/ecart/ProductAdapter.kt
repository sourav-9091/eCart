package com.example.ecart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val items: List<Any>,
    private val context: Context,
    private val onProductClickListener: OnProductClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_TITLE = 0
        private const val VIEW_TYPE_PRODUCT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is String) VIEW_TYPE_TITLE else VIEW_TYPE_PRODUCT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_TITLE) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_title, parent, false)
            TitleViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
            ProductViewHolder(view, onProductClickListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_TITLE) {
            (holder as TitleViewHolder).bind(items[position] as String)
        } else {
            (holder as ProductViewHolder).bind(items[position] as Product)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title_text)

        fun bind(title: String) {
            titleTextView.text = title
        }
    }

    class ProductViewHolder(itemView: View, private val onProductClickListener: OnProductClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val productName: TextView = itemView.findViewById(R.id.product_name)
        private val productPrice: TextView = itemView.findViewById(R.id.product_price)
        private val productImage: ImageView = itemView.findViewById(R.id.product_image)

        private lateinit var product: Product

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(product: Product) {
            this.product = product
            productName.text = product.title
            productPrice.text = "$${product.price}"
            Picasso.get().load(product.thumbnail).into(productImage)
        }

        override fun onClick(v: View?) {
            onProductClickListener.onProductClick(product)
        }
    }

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }
}
