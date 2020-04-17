package com.example.kotlinsocialmedia

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.String


class NewsDetailActivity : AppCompatActivity() {
    private var tvTitle: TextView? = null
    private var tvMain: TextView? = null
    private var tvLikes: TextView? = null
    private var tvAuthor: TextView? = null
    private var tvDate: TextView? = null
    private var img: ImageView? = null
    private lateinit var likeBtn: ImageButton
    var isLiked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        setContentView(R.layout.activity_news_detail)
        img = findViewById(R.id.ivAva)
        tvTitle = findViewById(R.id.tvTitle2)
        tvMain = findViewById(R.id.tvMain2)
        tvLikes = findViewById(R.id.tvLikes2)
        tvAuthor = findViewById(R.id.tvAuthor2)
        tvDate = findViewById(R.id.tvDate2)
        likeBtn = findViewById(R.id.ibLike)
        val news = intent.getParcelableExtra<Parcelable>("news") as News
        val like: Boolean = intent.extras!!.getBoolean("like")
        tvTitle?.text = news.title
        tvMain?.text = news.mainText
        tvLikes?.text = String.valueOf(news.likesCount)
        tvAuthor?.text = "by " + news.author
        tvDate?.text = news.date
        img?.setImageResource(news.images)
        news.setLike(like)
        isLiked = like
        likeBtn.setOnClickListener(View.OnClickListener {
            if (!isLiked) {
                news.setLike(true)
                news.setLikesCountt(news.likesCount + 1)
                tvLikes?.text = String.valueOf(news.likesCount)
                isLiked = true
                likeBtn.setBackgroundResource(R.drawable.ic_liked)
            } else {
                likeBtn.setBackgroundResource(R.drawable.ic_favorite)
                news.setLike(false)
                news.setLikesCountt(news.likesCount - 1)
                tvLikes?.text = String.valueOf(news.likesCount)
                isLiked = false
            }
        })
        if (isLiked) {
            likeBtn.setBackgroundResource(R.drawable.ic_liked)
        } else likeBtn.setBackgroundResource(R.drawable.ic_favorite)

    }
}