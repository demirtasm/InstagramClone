package com.example.instagramclone.adapter

import android.net.Uri
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AddPostGalleryAdapter(private val images: List<Uri>, private val onClick: (Uri) -> Unit) :
    RecyclerView.Adapter<AddPostGalleryAdapter.ImageViewHolder>() {

        inner class ImageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
       val imageView =ImageView(parent.context).apply {
           layoutParams = ViewGroup.LayoutParams(300, 300)
           scaleType = ImageView.ScaleType.CENTER_CROP
           setPadding(4, 4, 4, 4)
       }
        return ImageViewHolder(imageView)
    }

    override fun getItemCount(): Int  = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageURI(images[position])
        holder.imageView.setOnClickListener {
            onClick(images[position])
        }
    }
}