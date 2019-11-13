package com.bodhivruksha.show_api_data_in_multiple_tabs.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bodhivruksha.show_api_data_in_multiple_tabs.R
import com.bodhivruksha.show_api_data_in_multiple_tabs.model.User
import kotlinx.android.synthetic.main.user_recycler_item.view.*

class UserRVAdapter(var userList:List<User>, private val listener: Listener):
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.user_recycler_item, parent, false)
            .let { ViewHolder(it) }
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user, listener)
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val userItem1: TextView = view.rvUserItem1
        val userItem2: ImageView = view.rvUserItem2

        fun bind(user: User, listener: Listener) = with(itemView) {

            userItem1.text =   user.name

            setOnClickListener {
                listener.onClickItem(user)
            }
        }
    }


    interface Listener {
        fun onClickItem(user: User)
    }
}