package com.example.kotlinsocialmedia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.String


class NewsAdapter internal constructor(
    newsList: MutableList<News>,
    listener: ItemClickListener?,
    fragmentButtonListener: FragmentButtonListener?,
    fragmentLikeListener: FragmentLikeListener?
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val main_list: MutableList<News>
    private val listener: ItemClickListener?
    private val fragmentButtonListener: FragmentButtonListener?
    private val fragmentLikeListener: FragmentLikeListener?
    private val context: Context? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, null, false)
        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {
        val news: News = News.newsList.get(position)
        holder.date.text = news.date
        holder.author.text = news.author
        holder.title.text = news.title
        holder.mainText.text = news.mainText
        holder.likesCount.text = String.valueOf(news.likesCount)
        holder.commentsCount.text = String.valueOf(news.commentsCount)
        holder.itemView.setOnClickListener { listener?.itemClic(position, news) }

        holder.likeBtn.setOnClickListener { listener?.likeClic(position, news) }
        if (news.Liked() === true) {
            holder.likeBtn.setImageResource(R.drawable.ic_liked)
        } else holder.likeBtn.setImageResource(R.drawable.ic_favorite)
    }

    override fun getItemCount(): Int {
        return News.newsList.size
    }

    inner class NewsViewHolder(itemView: View) :
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
            likeBtn =
                itemView.findViewById<View>(R.id.ibLike) as ImageView
        }
    }

    internal interface ItemClickListener {
        fun itemClic(position: Int, item: News)
        fun likeClic(position: Int, item: News)
    }

    interface FragmentButtonListener {
        fun myClick(news: News?, option: Int)
    }

    interface FragmentLikeListener {
        fun removeItemLike(news: News?)
    }

    fun removeLike(news: News) {
        val n: Int = News.newsList.indexOf(news)
        news.setLike(false)
        news.setLikesCountt(news.likesCount - 1)
        News.newsList.set(n, news)
        main_list[n] = news
        this.notifyItemChanged(n)
    }

    init {
        News.newsList = newsList
        main_list = newsList
        this.listener = listener
        this.fragmentButtonListener = fragmentButtonListener
        this.fragmentLikeListener = fragmentLikeListener
    }
}

private fun <E> List<E>.set(n: Int, news: E) {

}
