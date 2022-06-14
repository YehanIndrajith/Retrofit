package com.example.restapiretrofit203.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapiretrofit203.R


class RecyclerViewCustomAdapter(private val postList: List<Post>) : RecyclerView.Adapter<RecyclerViewCustomAdapter.ViewHolder>() {

    // change to different subjects from rx.subjects to get different behavior
    // BehaviorSubject for example allows to receive last event on subscribe
    // PublishSubject sends events only after subscribing on the other hand which is desirable for clicks
    private val clickSubject = PublishSubject.create<String>()
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val postListItem = postList[position]

        // sets the view holder's textViewPostId to the postId from postListItem
        holder.textViewPostId.text = postListItem.id.toString()

        // sets the view holder's textViewTitle to the title from postListItem
        holder.textViewTitle.text = postListItem.title

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return postList.size
    }

    // Holds the views for adding it to image and text
    public inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewPostId: TextView = itemView.findViewById(R.id.textView_post_id)
        val textViewTitle: TextView = itemView.findViewById(R.id.textView_title)
        init {
            itemView.setOnClickListener {
                clickSubject.onNext(textViewPostId.text as String)
            }
        }
    }
    val clickEvent : Observable<String> = clickSubject
}