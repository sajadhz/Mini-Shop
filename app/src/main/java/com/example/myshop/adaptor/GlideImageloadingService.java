package com.example.myshop.adaptor;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ss.com.bannerslider.ImageLoadingService;
public class GlideImageloadingService implements ImageLoadingService {
    Context context;

    public GlideImageloadingService(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Glide.with(context).load(resource).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Glide.with(context).load(url).placeholder(placeHolder).error(errorDrawable).into(imageView);
    }
}
