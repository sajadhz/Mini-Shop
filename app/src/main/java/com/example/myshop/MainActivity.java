package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daasuu.cat.CountAnimationTextView;
import com.example.myshop.View.BasketActivity;
import com.example.myshop.adaptor.BestPriceAdaptor;
import com.example.myshop.adaptor.GlideImageloadingService;
import com.example.myshop.adaptor.MainSliderAdapter;
import com.example.myshop.adaptor.ProductAdaptor;
import com.example.myshop.database.ShopDatabase;
import com.example.myshop.listener.OnAddProduct;
import com.example.myshop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.adapters.SliderAdapter;

public class MainActivity extends AppCompatActivity {
    ShopDatabase database;
    Executor executor;
    CountAnimationTextView textViewCounter;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intilizeing
        database = ShopDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();
        textViewCounter = findViewById(R.id.count_animation_textView);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        ImageView imageViewAbout = findViewById(R.id.imageViewAbout);
        ImageView imageViewBasket = findViewById(R.id.imageViewBasket);
        //setting slider
        Slider.init(new GlideImageloadingService(this));
        Slider slider = findViewById(R.id.banner_slider1);
        slider.setAdapter(new MainSliderAdapter());
        //setting recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMain);
        RecyclerView recyclerViewBestPrice = findViewById(R.id.recyclerViewBestPrice);
        //calling updateCounter to updating shoping basket imageView
        updateCounter();
        //itilizing onAddproduct interface
        OnAddProduct onAddProduct = new OnAddProduct() {
            @Override
            public void onProductAdded() {
            updateCounter();
            }
        };
        //set recyclerview adaptor
        recyclerView.setAdapter(new ProductAdaptor(getData(),this,onAddProduct));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        //Listeners
        //Listener for Basket imageView
        imageViewBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                startActivity(intent);
            }
        });
        //Listener for About imageView
        imageViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        //Setup recyclerview BestPrice
        recyclerViewBestPrice.setAdapter(new BestPriceAdaptor(getDataBestPrice(),MainActivity.this));
        recyclerViewBestPrice.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, false));

    }
        //Getting main recyclerview Data
    public List<Product> getData(){
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();

        product1.setUrl("https://dkstatics-public.digikala.com/digikala-products/3350711.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product2.setUrl("https://dkstatics-public.digikala.com/digikala-products/c0fdbdb0ba48f003b7e3e6ee64a7ce66956d05ff_1625385547.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product3.setUrl("https://dkstatics-public.digikala.com/digikala-products/25a5218f6b9598f85dc0192276b0a18cbc097ee5_1605693044.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product4.setUrl("https://da8t9y7300ntx.cloudfront.net/wp-content/uploads/sites/19/2020/06/451.OX.1180.OX-808x1024.jpg");
        product5.setUrl("https://dkstatics-public.digikala.com/digikala-products/112341058.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product1.setName("Casio");
        product2.setName("Rolex");
        product3.setName("Omega");
        product4.setName("Blancpain");
        product5.setName("Tudor");
        product1.setPrice(885000);
        product2.setPrice(430000);
        product3.setPrice(535000);
        product4.setPrice(155000);
        product5.setPrice(945000);
        product1.setRawPrice(200000);
        product2.setRawPrice(200000);
        product3.setRawPrice(200000);
        product4.setRawPrice(200000);
        product5.setRawPrice(200000);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        return productList;
    }
    //Calling onResume to run updateCounter func to updating Baket Icon number immediately
    @Override
    protected void onResume() {
        super.onResume();
        updateCounter();
    }

    //Func updateCounter _> updating Baket icon Number
    public void updateCounter(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                int sum = database.productDou().getSumQuantity();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewCounter.setText(sum + "");
                    }
                });
            }
        });
    }


    //Get Data for BestPrice Recyclerview
    public List<Product> getDataBestPrice(){
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();

        product1.setUrl("https://dkstatics-public.digikala.com/digikala-products/111059101.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product2.setUrl("https://dkstatics-public.digikala.com/digikala-products/9f6f69f9501855dae03de6a404fbccff59f40bc1_1610798881.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product3.setUrl("https://dkstatics-public.digikala.com/digikala-products/273597f71d35b907daa45fbdaec7432e4fc416af_1612247952.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product4.setUrl("https://dkstatics-public.digikala.com/digikala-products/1a092f2ddbd86596175c5dcdfc947c3786183e4d.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product5.setUrl("https://dkstatics-public.digikala.com/digikala-products/9c1f5198e8e75be1f6aa17e85909b7a4919ec77f_1614538155.jpg?x-oss-process=image/resize,m_lfit,h_600,w_600/quality,q_90");
        product1.setName("Redmi هنزفری بیسیم");
        product2.setName("Galexi EarBud Pro هنزفری بیسیم");
        product3.setName("Lenovou هنزفری بیسیم");
        product4.setName("هنزفری بیسیم هایلو T15");
        product5.setName("هنزفری اپل مدل AirPods Pro");
        product1.setPrice(400000);
        product2.setPrice(3200000);
        product3.setPrice(290000);
        product4.setPrice(470000);
        product5.setPrice(5200000);
        product1.setRawPrice(320000);
        product2.setRawPrice(3000000);
        product3.setRawPrice(200000);
        product4.setRawPrice(400000);
        product5.setRawPrice(4900000);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        return productList;
    }
}