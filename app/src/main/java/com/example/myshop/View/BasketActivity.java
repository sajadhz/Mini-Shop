package com.example.myshop.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.myshop.MainActivity;
import com.example.myshop.R;
import com.example.myshop.adaptor.BasketAdaptor;
import com.example.myshop.database.ShopDatabase;
import com.example.myshop.listener.OnAdaptorUpdate;
import com.example.myshop.model.Product;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
//Basket Activity
public class BasketActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        //Intilizing
        final ShopDatabase database = ShopDatabase.getInstance(this);
        Executor executor = Executors.newSingleThreadExecutor();
        final RecyclerView recyclerViewBasket = findViewById(R.id.recyclerViewBasket);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Get Products from Database
                List<Product> productList = database.productDou().getAllProduct();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //setting up Basket Adaptor
                        BasketAdaptor basketAdaptor = new BasketAdaptor(productList,getApplicationContext());
                        basketAdaptor.setOnAdaptorUpdate(new OnAdaptorUpdate() {
                            @Override
                            public void onAdaptorUpdate() {
                             basketAdaptor.notifyDataSetChanged();
                            }
                        });
                        //Setting up RecyclerView
                        recyclerViewBasket.setAdapter(basketAdaptor);
                        recyclerViewBasket.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });

            }
        });
    }
}
