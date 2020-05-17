package com.example.bakingapp.util;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class CustomBindings {

    @BindingAdapter(value = "imageUrl")
    public static void bindImageUrl(final ImageView imageView, final String imageUrl) {
        if (imageUrl.isEmpty()) {
            imageView.setVisibility(View.GONE);
            return;
        }
        Glide.with(imageView).load(imageUrl).into(imageView);
    }

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(
            final RecyclerView recyclerView, final RecyclerView.Adapter<?> adapter
    ) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("visibleOrGone")
    public static void setVisibleOrGone(final View view, final Boolean visible) {
        if (visible != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }
}