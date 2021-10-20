package com.rahul.moviesearch.component


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Image view extension class that helps to load an image
 *
 * and other Image view Ui modifications
 *
 */


/**
 * Method gets the Image from the path specified in the parameter
 * this also circle crops the Image.
 * @param path  image url
 */
@BindingAdapter("getImage")
fun AppCompatImageView.getImage(path: String?) {
    Glide.with(this.context).load(path).into(this)
}