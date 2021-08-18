package com.example.myshop.adaptor;

import android.content.Context;
import android.graphics.Paint;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.model.Product;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

//Adaptor
public class BestPriceAdaptor extends RecyclerView.Adapter<BestPriceAdaptor.BestPriceViewHolder>{
    List<Product> productList;
    Context context;
//Constructor
    public BestPriceAdaptor(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public BestPriceViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_best_price,parent,false);
        return new BestPriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BestPriceViewHolder holder, int position) {
        Product product = productList.get(position);
        Glide.with(context).load(product.getUrl()).into(holder.imageViewProduct);
        holder.textViewprice.setText(product.getPrice() + "T");
        holder.textViewprice.setPaintFlags( holder.textViewprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.textViewbestPrice.setText(product.getRawPrice() + "T");

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    //ViewHolder
    class BestPriceViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewprice;
        TextView textViewbestPrice;
        public BestPriceViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewProduct =itemView.findViewById(R.id.imageViewBestProduct);
            textViewprice =itemView.findViewById(R.id.textViewMyPrice);
            textViewbestPrice =itemView.findViewById(R.id.textViewBestPrice);
        }
    }
}
