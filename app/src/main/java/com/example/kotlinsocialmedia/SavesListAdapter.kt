package com.example.kotlinsocialmedia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.String


class SavesListAdapter : RecyclerView.Adapter<SavesListAdapter.SavesListViewHolder> {
    private var newsList: List<News>
    private val context: Context? = null
    private var listener: ItemClickListener? = null
    private var fragmentLikeListener: FragmentLikeListener? = null

    constructor(newsList: List<News>) {
        this.newsList = newsList
    }

    constructor(
        newsList: List<News>, listener: ItemClickListener?,
        fragmentLikeListener: FragmentLikeListener?
    ) {
        this.newsList = newsList
        this.listener = listener
        this.fragmentLikeListener = fragmentLikeListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavesListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, null, false)
        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return SavesListViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SavesListViewHolder,
        position: Int
    ) {
        val news = newsList[position]
        holder.date.text = news.date
        holder.author.text = news.author
        holder.title.text = news.title
        holder.mainText.text = news.mainText
        holder.likesCount.text = String.valueOf(news.likesCount)
        holder.commentsCount.text = String.valueOf(news.commentsCount)
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener?.itemClic(position, news)
            }
        }
        holder.likeBtn.setOnClickListener {
            if (fragmentLikeListener != null) fragmentLikeListener?.removeItemLike(
                news
            )
        }

        if (news.Liked() === true) {
            holder.likeBtn.setImageResource(R.drawable.ic_liked)
        } else holder.likeBtn.setImageResource(R.drawable.ic_favorite)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class SavesListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var author: TextView
        var date: TextView
        var title: TextView
        var mainText: TextView
        var likesCount: TextView
        var commentsCount: TextView
        var likeBtn: ImageView

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
        fun itemClic(position: Int, item: News)
        fun likeClic(position: Int, item: News)
    }

    interface FragmentLikeListener {
        fun removeItemLike(news: News?)
    }
}
