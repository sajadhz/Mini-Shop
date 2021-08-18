package com.example.myshop.adaptor;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;
//Adaptor
public class MainSliderAdapter extends SliderAdapter {
    @Override
    public int getItemCount() {
        return 3;
    }
    //Setting Slider images
    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        switch (position) {
            case 0:
                imageSlideViewHolder.bindImageSlide("https://dkstatics-public.digikala.com/digikala-adservice-banners/510888409db0a4882c1365b60ade395b3f99d388_1625934164.gif");
                break;
            case 1:
                imageSlideViewHolder.bindImageSlide("https://dkstatics-public.digikala.com/digikala-adservice-banners/7bdf7b2708aa4bff3b86eea627cc615677f684f8_1626240833.gif");
                break;
            case 2:
                imageSlideViewHolder.bindImageSlide("https://www.auburn.co.uk/wp-content/uploads/2016/04/willows-slider-laptop.jpg");
                break;
        }
    }
}
