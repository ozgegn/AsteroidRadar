package com.ozgegn.asteroidradar.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.asteroidradar.R
import com.ozgegn.asteroidradar.data.Asteroid
import com.ozgegn.asteroidradar.ui.main.AsteroidListAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_warn_red)
    } else {
        imageView.setImageResource(R.drawable.ic_warn_yellow)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>?) {
    val adapter = recyclerView.adapter as AsteroidListAdapter
    adapter.submitList(data ?: listOf()) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("loadUrl")
fun bindImageUrl(imageView: ImageView?, url: String?) {
    imageView?.let {
        Picasso.get().load(url).placeholder(R.drawable.placeholder_picture_of_day)
            .error(R.drawable.ic_broken_image).into(imageView)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
