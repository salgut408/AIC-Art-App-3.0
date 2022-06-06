package com.example.android.aicartapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class Adapters {
    companion object {
        @BindingAdapter("pictureUrl")
        fun bindUriToImage(imageView: ImageView, strUrl: String) {
            Glide.with(imageView.context)
                .load(strUrl)
                .into(imageView)
        }
    }
}