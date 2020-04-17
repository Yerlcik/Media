package com.example.kotlinsocialmedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private var newsList: List<News>
    private var listener: ItemClickListener? = null


    constructor(newsList: List<News>, listener: ItemClickListener?) {
        this.newsList = newsList
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val context = parent.context
        val layoutIdForNewsItem = R.layout.item_news
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutIdForNewsItem, null, false)
        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.author.text = "by " + news.author
        holder.date.text = news.date
        holder.title.text = news.title
        holder.mainText.text = news.mainText
        holder.likesCount.text = news.likesCount.toString()
        holder.commentsCount.text = news.commentsCount.toString()
        if (news.Liked()) {
            holder.likeBtn.setImageResource(R.drawable.ic_liked)

        } else holder.likeBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener?.itemClick(position, news)
            }
        }
        holder.likeBtn.setOnClickListener {
            if (listener != null) {
                listener?.likeClick(position, news)
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var author: TextView
        var date: TextView
        var title: TextView
        var mainText: TextView
        var likesCount: TextView
        var commentsCount: TextView
        var likeBtn: ImageButton

        init {
            author = itemView.findViewById(R.id.tvAuthor)
            date = itemView.findViewById(R.id.tvDate)
            title = itemView.findViewById(R.id.tvTitle)
            mainText = itemView.findViewById(R.id.tvMain)
            likesCount = itemView.findViewById(R.id.tvLike)
            commentsCount = itemView.findViewById(R.id.tvCom)
            likeBtn = itemView.findViewById(R.id.ibLike)
        }
    }

    interface ItemClickListener {
        fun itemClick(position: Int, item: News?)
        fun likeClick(position: Int, item: News?)
    }
}