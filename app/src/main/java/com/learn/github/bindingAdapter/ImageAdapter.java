package com.learn.github.bindingAdapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageAdapter {

    @BindingAdapter(value = {"imageUrl","holder"})
    public static void loadImage(ImageView view,String imageUrl, Drawable holder) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .placeholder(holder).apply(new RequestOptions().circleCrop())
                .into(view);
    }
}
