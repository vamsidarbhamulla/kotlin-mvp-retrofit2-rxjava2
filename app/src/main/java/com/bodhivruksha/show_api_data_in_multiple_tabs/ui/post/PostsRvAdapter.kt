package com.bodhivruksha.show_api_data_in_multiple_tabs.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bodhivruksha.show_api_data_in_multiple_tabs.R
import com.bodhivruksha.show_api_data_in_multiple_tabs.model.Post
import kotlinx.android.synthetic.main.post_recycler_item.view.*

class PostsRvAdapter(var postsList: List<Post>, private val listener: Listener):
    RecyclerView.Adapter<PostsRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.post_recycler_item, parent, false)
            .let { view ->
                ViewHolder(
                    view
                )
            }
    }

    override fun getItemCount(): Int = postsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postsList[position]
        holder.bind(post, listener)
        /*holder.postId.text = "Post id: "+post.id.toString()
        holder.postUserId.text = "Post user id: "+post.userId.toString()
        holder.postTitle.text = "Post title: " + post.title
        holder.postBody.text = "Post body: "+post.body*/
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val postImage: ImageView = view.rvPostsItem1
        val postTitle: TextView = view.rvPostsItem2

        fun bind(post: Post, listener: Listener) = with(itemView) {
            postTitle.text = post.title

            setOnClickListener {
                listener.onClickItem(post)
            }
        }

    }


    interface Listener {
        fun onClickItem(post: Post)
    }
}