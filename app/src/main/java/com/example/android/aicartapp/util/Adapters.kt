package com.example.android.aicartapp.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("pictureUrl")
fun bindUriToImage(imageView: ImageView, strUrl: String) {
    Glide.with(imageView.context)
        .load(strUrl)
        .into(imageView)
}

@BindingAdapter("loadingWheel")
fun goneIfNotNull(view: View, it: Int) {
    view.visibility = if (it != 0) View.GONE else View.VISIBLE
}