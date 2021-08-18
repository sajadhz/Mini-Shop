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
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.database.ShopDatabase;
import com.example.myshop.listener.OnAdaptorUpdate;
import com.example.myshop.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//Adaptor
public class BasketAdaptor extends RecyclerView.Adapter<BasketAdaptor.BasketViewHolder> {
    List<Product> productList;
    Context context;
    ShopDatabase database;
    Executor executor = Executors.newSingleThreadExecutor();
    OnAdaptorUpdate onAdaptorUpdate;

    //Constructor
    public void setOnAdaptorUpdate(OnAdaptorUpdate onAdaptorUpdate) {
        this.onAdaptorUpdate = onAdaptorUpdate;
    }

    //Constructor
    public BasketAdaptor(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
        this.database = ShopDatabase.getInstance(context);
        this.onAdaptorUpdate = onAdaptorUpdate;
    }

    @NonNull
    @NotNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_basket, parent, false);
        return new BasketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BasketViewHolder holder, int position) {
        Product product = productList.get(position);
        Glide.with(context)
                .load(product.getUrl())
                .into(holder.imageViewProduct);
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductPrice.setText(product.getPrice() + "");
        holder.textViewProductRawPrice.setText(product.getRawPrice() + "");
        holder.textViewProductQuintity.setText(product.getQuantity() + "");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    //View holder
    class BasketViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewProduct;
        ImageView imageViewDelete;
        TextView textViewProductName;
        TextView textViewProductPrice;
        TextView textViewProductRawPrice;
        TextView textViewProductQuintity;
        ImageView buttonIncQuantity;
        ImageView buttonDecQuantity;

        public BasketViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewBasketProduct);
            textViewProductName = itemView.findViewById(R.id.textViewBasketProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            textViewProductRawPrice = itemView.findViewById(R.id.textViewProductRawPrice);
            textViewProductQuintity = itemView.findViewById(R.id.textViewProductQuintity);
            buttonIncQuantity = itemView.findViewById(R.id.imageViewAdd);
            buttonDecQuantity = itemView.findViewById(R.id.imageViewMinus);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            //Listeners
            buttonIncQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    incProductToQuintity(productList.get(getAbsoluteAdapterPosition()));
                }
            });
            buttonDecQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decProductToQuintity(productList.get(getAbsoluteAdapterPosition()));
                }
            });
            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteProduct(productList.get(getAbsoluteAdapterPosition()));
                }
            });
        }

    }

        //Function increasing product
    public void incProductToQuintity(Product product) {
        product.setQuantity(product.getQuantity() + 1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                database.productDou().addProduct(product);
            }
        });
        onAdaptorUpdate.onAdaptorUpdate();
    }

        //Function reducing product
    public void decProductToQuintity(Product product) {
        if (product.getQuantity() > 1) {
            product.setQuantity(product.getQuantity() - 1);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    database.productDou().addProduct(product);
                }
            });
            onAdaptorUpdate.onAdaptorUpdate();
        }


    }
        //Function deleteing product
    public void deleteProduct(Product product) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    database.productDou().deleteProduct(product);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        productList.remove(product);
        onAdaptorUpdate.onAdaptorUpdate();
    }

}
