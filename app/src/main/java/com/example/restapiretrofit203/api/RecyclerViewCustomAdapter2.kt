package com.example.restapiretrofit203.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapiretrofit203.R

class RecyclerViewCustomAdapter2(private val commentList: List<Comments>) : RecyclerView.Adapter<RecyclerViewCustomAdapter2.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_comments, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val postListItem = commentList[position]

        holder.textViewCommentId.text = postListItem.id.toString()
        holder.textViewName.text = postListItem.name.toString()
        holder.textViewEmail.text = postListItem.email.toString()
        holder.textViewBody.text = postListItem.body.toString()

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return commentList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewCommentId: TextView = itemView.findViewById(R.id.textView_comment_id)
        val textViewName: TextView = itemView.findViewById(R.id.textView_name)
        val textViewEmail: TextView = itemView.findViewById(R.id.textView_email)
        val textViewBody: TextView = itemView.findViewById(R.id.textView_body)
    }
}