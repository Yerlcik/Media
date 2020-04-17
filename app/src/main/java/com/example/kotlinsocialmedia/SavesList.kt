package com.example.kotlinsocialmedia

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class SavesList : Fragment() {
    var recyclerView: RecyclerView? = null
    private var adapter: SavesListAdapter? = null
    private var newsList: MutableList<News>? = null
    private var listener: SavesListAdapter.ItemClickListener? = null
    private var fragmentLikeListener: SavesListAdapter.FragmentLikeListener? = null
    var isLiked = false
    private val newsAdapter: NewsAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater
            .inflate(R.layout.fav_fragment, container, false) as ViewGroup
        recyclerView = rootView.findViewById(R.id.FavrecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        listener = object : SavesListAdapter.ItemClickListener {
            override fun itemClic(position: Int, item: News) {
                val intent = Intent(activity, NewsDetailActivity::class.java)
                intent.putExtra("news", item)
                startActivity(intent)
            }

            override fun likeClic(position: Int, item: News) {
                isLiked = item.Liked()
                isLiked = if (!isLiked) {
                    item.setLike(true)
                    item.setLikesCountt(item.likesCount + 1)
                    true
                } else {
                    item.setLike(false)
                    item.setLikesCountt(item.likesCount - 1)
                    false
                }
                newsAdapter?.notifyItemChanged(position)
            }
        }
        fragmentLikeListener = object : SavesListAdapter.FragmentLikeListener {
            override fun removeItemLike(news: News?) {
                (activity as MainActivity?)?.removeItemLike(news)
            }
        }
        newsList = ArrayList()
        adapter = SavesListAdapter(newsList as ArrayList<News>, listener, fragmentLikeListener)
        recyclerView?.adapter = adapter
        return rootView
    }

    fun saveNews(news: News) {
        newsList?.add(news)
        recyclerView?.adapter?.notifyItemInserted(newsList!!.size - 1)
    }

    fun removeNews(news: News?) {
        if (newsList?.indexOf(news) == 0) {
            newsList?.remove(news)
        } else {
            val position = newsList?.indexOf(news)
            newsList?.remove(news)
            if (position != null) {
                recyclerView?.adapter?.notifyItemRemoved(position)
            }
        }
    }

    fun removeLike(news: News?) {
        val n = newsList?.indexOf(news)
        removeNews(news)
        if (n != null) {
            adapter?.notifyItemRemoved(n)
        }
    }
}
