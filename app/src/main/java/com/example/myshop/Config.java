package com.example.myshop;

import android.app.Application;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
//Config
public class Config extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Setting Font
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/vazir.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
        //....
    }
}
