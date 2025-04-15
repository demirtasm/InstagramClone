package com.example.instagramclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.databinding.RecyclerRowBinding
import com.example.instagramclone.model.Post
import com.squareup.picasso.Picasso

class FeedAdapter(val postList:ArrayList<Post>): RecyclerView.Adapter<FeedAdapter.PostHolder>() {


     class PostHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
       val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.rvEmail.text = postList.get(position).email
        holder.binding.rvCommentText.text = postList.get(position).comment
        Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.rvImg)
    }
}