package com.example.lesson6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ArticleAdapter(val context: Context, val articleSource:List<ArticleInfo>): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    var onItemClick: ((ArticleInfo) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.items_recycle,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return articleSource.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text=articleSource[position].title
        Picasso.get().load(articleSource[position].imgURL).into(holder.image)
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.articleTitle)
        val image = view.findViewById<ImageView>(R.id.articleImage)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(articleSource[adapterPosition])
            }
        }
    }
}