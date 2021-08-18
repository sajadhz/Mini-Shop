package com.example.myshop.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.database.ShopDatabase;
import com.example.myshop.listener.OnAdaptorUpdate;
import com.example.myshop.listener.OnAddProduct;
import com.example.myshop.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
//Adaptor
public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.MainProductViewHolder> {
    List<Product> productList;
    Context context;
    OnAddProduct onAddProduct;
//Constructor
    public ProductAdaptor(List<Product> productList, Context context, OnAddProduct onAddProduct) {
        this.productList = productList;
        this.context = context;
        this.onAddProduct = onAddProduct;
    }

    @NonNull
    @NotNull
    @Override
    public MainProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false);
        return new MainProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainProductViewHolder holder, int position) {
        Product product = productList.get(position);
        Glide.with(context)
                .load(product.getUrl())
                .into(holder.imageViewProduct);

        holder.textViewProductName.setText(product.getName());
        holder.textViewProductRawPrice.setText(product.getRawPrice() + "T");
        holder.textViewProductPrice.setText(product.getPrice() + "T");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    //View Holder
    class MainProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;
        TextView textViewProductRawPrice;
        Button buttonAddToBasket;

        public MainProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductPrice = itemView.findViewById(R.id.textViewPrice);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductRawPrice = itemView.findViewById(R.id.textViewRawPrice);
            buttonAddToBasket = itemView.findViewById(R.id.buttonAddToBasket);
            buttonAddToBasket.setOnClickListener(this);

        }
    //Listener ViewHolder
        @Override
        public void onClick(View v) {
            Product product = productList.get(getAbsoluteAdapterPosition());
            product.setQuantity(product.getQuantity() + 1);
            ShopDatabase database = ShopDatabase.getInstance(context);
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    database.productDou().addProduct(product);
                }
            });
            onAddProduct.onProductAdded();
        }
    }
}

