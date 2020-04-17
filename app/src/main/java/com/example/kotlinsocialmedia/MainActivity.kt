package com.example.kotlinsocialmedia

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var newsAdapter: NewsAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var listener: NewsAdapter.ItemClickListener? = null
    private var like: ImageButton? = null
    var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            isLiked = savedInstanceState.getBoolean("liked")
        }
        listener = object : NewsAdapter.ItemClickListener {
            override fun itemClick(position: Int, item: News?) {
                val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
                intent.putExtra("news", item)
                if (item != null) {
                    intent.putExtra("like", item.Liked())
                }
                startActivity(intent)
            }

            override fun likeClick(
                position: Int,
                item: News?
            ) {
                if (item != null) {
                    isLiked = item.Liked()
                }
                if (isLiked == false) {
                    if (item != null) {
                        item.setLike(true)
                    }
                    if (item != null) {
                        item.likesCount = item.likesCount + 1
                    }
                    isLiked = true
                } else {
                    item?.setLike(false)
                    if (item != null) {
                        item?.likesCount = item.likesCount - 1
                    }
                    isLiked = false
                }
                newsAdapter?.notifyItemChanged(position)
            }
        }
        val layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = layoutManager
        newsAdapter = NewsAdapter(newsGenerator(), listener)
        recyclerView?.adapter = newsAdapter
        like = findViewById(R.id.ibLike)
    }

    private fun newsGenerator(): List<News> {
        val loremIpsum =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        val items: MutableList<News> = ArrayList()
        val n1 =
            News("02/21/2020 11:45", "Asma Moyeed", "Loved ones", getString(R.string.first), 1, 5)
        n1.images = R.drawable.a1
        val n2 = News(
            "02/22/2020 01:21",
            "Toni Plant",
            "The Lamb and The Wild Beasts",
            getString(R.string.second),
            1,
            5
        )
        n2.images = R.drawable.a3
        val n3 =
            News("02/22/2020 01:20", "S. F. Tilly", "Angry world", getString(R.string.third), 5, 5)
        n3.images = R.drawable.a2
        val n4 = News("02/22/2020 01:29", "Zaa_aaah", "Me", getString(R.string.fo), 0, 5)
        n4.images = R.drawable.av4
        val n5 =
            News("02/22/2020 01:21", "cmukti", "Thrubbing silencees", getString(R.string.fiv), 1, 5)
        n5.images = R.drawable.av5
        val n6 = News("02/22/2020 01:22", "Bsn", "Celest", getString(R.string.six), 15, 5)
        n6.images = R.drawable.av6
        val n7 = News(
            "01/27/2020 10:17",
            "Ankita Chaturvedi",
            "Being Strong Alone",
            getString(R.string.seven),
            195,
            5
        )
        n7.images = R.drawable.av7
        val n8 = News(
            "01/24/2020 04:32",
            "Lykeus_",
            "_______Help me Stay_______",
            getString(R.string.ei),
            177,
            55
        )
        n8.images = R.drawable.av8
        val n9 = News(
            "02/14/2020 06:34",
            "♛_\uD835\uDC40\uD835\uDCBE\uD835\uDCCF\uD835\uDCB6\uD835\uDCC3_♛",
            "A Glorious Mess She is",
            getString(R.string.ni),
            174,
            54
        )
        n9.images = R.drawable.av9
        val n10 = News(
            "01/30/2020 03:45",
            "Ankita Chaturvedi_",
            "Struggle",
            getString(R.string.ten),
            172,
            55
        )
        items.add(n1)
        items.add(n2)
        items.add(n3)
        items.add(n4)
        items.add(n5)
        items.add(n6)
        items.add(n7)
        items.add(n8)
        items.add(n9)
        items.add(n10)
        return items
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("liked", isLiked)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString("liked")
    }
}