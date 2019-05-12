package com.github.jaydeepw.assignment01.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.jaydeepw.assignment01.R
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import kotlinx.android.synthetic.main.list_item.view.*

class Adapter(val items : ArrayList<Album>?, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = items?.get(position)
        holder.albumTitle?.text = album?.title
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items?.size!!
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val albumTitle = view.textview_album
}