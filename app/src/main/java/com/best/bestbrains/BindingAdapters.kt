package com.best.bestbrains

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

// Submits image from Url to ImageView
@BindingAdapter("submitImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
        val imgUri = imgUrl!!.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)

            .apply(
                RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(imgView)

}