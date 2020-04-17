package com.example.kotlinsocialmedia

import android.os.Bundle
import android.os.Parcelable
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewsDetailActivity : AppCompatActivity() {
    private var tvTitle: TextView? = null
    private var tvMain: TextView? = null
    private var tvLikes: TextView? = null
    private var tvAuthor: TextView? = null
    private var tvDate: TextView? = null
    private var img: ImageView? = null
    private var likeBtn: ImageButton? = null
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
        val like: Boolean = intent.extras?.getBoolean("like")!!
        tvTitle?.text = news.title
        tvMain?.text = news.mainText
        tvLikes?.text = news.likesCount.toString()
        tvAuthor?.text = "by " + news.author
        tvDate?.text = news.date
        img?.setImageResource(news.images)
        news.setLike(like)
        if (!like) tvAuthor?.text = "false" else tvAuthor?.text = "tr"
        if (news.Liked()) {
            likeBtn?.setImageResource(R.drawable.ic_liked)
        } else likeBtn?.setImageResource(R.drawable.ic_favorite_border_black_24dp)
    }
}