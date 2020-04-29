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


class NewsList : Fragment() {
    var recyclerView: RecyclerView? = null
    var recyclerView2: RecyclerView? = null
    var storiesModelArrayList: ArrayList<StoriesModel> =
        ArrayList<StoriesModel>()
    var adapterStories: RecyclerViewAdapterStories? = null
    private val adapter: NewsAdapter? = null
    var isLiked = false
    private var newsAdapter: NewsAdapter? = null
    private var like: ImageButton? = null
    private var listener: NewsAdapter.ItemClickListener? = null
    private var fragmentButtonListener: NewsAdapter.FragmentButtonListener? = null
    private var fragmentLikeListener: NewsAdapter.FragmentLikeListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater
            .inflate(R.layout.page, container, false) as ViewGroup
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView2 = rootView.findViewById<View>(R.id.recy_stories) as RecyclerView
        val layoutManager1: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        (layoutManager1 as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
        recyclerView2?.layoutManager = layoutManager1
        adapterStories = RecyclerViewAdapterStories(requireActivity(), storiesModelArrayList)
        recyclerView2?.adapter = adapterStories
        populaterecyclerviewstories()
        listener = object : NewsAdapter.ItemClickListener {
            override fun itemClic(position: Int, item: News) {
                val intent = Intent(activity, NewsDetailActivity::class.java)
                intent.putExtra("news", item)
                intent.putExtra("like", item.Liked())
                startActivity(intent)
            }

            override fun likeClic(position: Int, item: News) {
                isLiked = item.Liked()
                isLiked = if (!isLiked) {
                    item.setLike(true)
                    item.setLikesCountt(item.likesCount + 1)
                    fragmentButtonListener!!.myClick(item, 1)
                    true
                } else {
                    item.setLike(false)
                    fragmentLikeListener!!.removeItemLike(item)
                    false
                }
                newsAdapter?.notifyItemChanged(position)
            }
        }
        fragmentButtonListener = object : NewsAdapter.FragmentButtonListener {
            override fun myClick(news: News?, option: Int) {
                if (news != null) {
                    (activity as MainActivity?)?.myClick(news, option)
                }
            }
        }
        fragmentLikeListener = object : NewsAdapter.FragmentLikeListener {
            override fun removeItemLike(news: News?) {
                (activity as MainActivity?)?.removeItemLike(news)
            }
        }
        newsAdapter =
            NewsAdapter(
                newsGenerator() as MutableList<News>,
                listener,
                fragmentButtonListener,
                fragmentLikeListener
            )
        recyclerView?.adapter = newsAdapter
        recyclerView?.isNestedScrollingEnabled = false
        like = rootView.findViewById(R.id.ibLike)
        return rootView
    }

    private fun populaterecyclerviewstories() {
        var storiesModel = StoriesModel("nature", R.drawable.a1)
        storiesModelArrayList.add(storiesModel)
        storiesModel = StoriesModel("rachel", R.drawable.a2)
        storiesModelArrayList.add(storiesModel)
        storiesModel = StoriesModel("barat", R.drawable.a3)
        storiesModelArrayList.add(storiesModel)
        storiesModel = StoriesModel("birzhan", R.drawable.propic4)
        storiesModelArrayList.add(storiesModel)
        storiesModel = StoriesModel("ztb", R.drawable.av5)
        storiesModelArrayList.add(storiesModel)
        storiesModel = StoriesModel("qumash", R.drawable.av8)
        storiesModelArrayList.add(storiesModel)
    }

    private fun newsGenerator(): List<News> {
        val loremIpsum =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        val items: MutableList<News> = ArrayList()
        val n1 =
            News("11/01/2020 09:08", "Carl Breez", "My life", getString(R.string.first), 1, 5)
        n1.setImagess(R.drawable.a1)
        val n2 = News(
            "12/01/2020 21:11",
            "Black Jack",
            "The rich man",
            getString(R.string.second),
            1,
            5
        )
        n2.setImagess(R.drawable.a3)
        val n3 =
            News("12/01/2020 01:20", "C. Ronaldo", "Goal", getString(R.string.third), 5, 5)
        n3.setImagess(R.drawable.a2)
        val n4 = News("12/01/2020 05:20", "Baha", "Me", getString(R.string.fo), 0, 5)
        n4.setImagess(R.drawable.av4)
        val n5 =
            News("20/02/2020 01:21", "Smurf", "Thrubbing silencees", getString(R.string.fiv), 1, 5)
        n5.setImagess(R.drawable.av5)
        val n6 = News("05/02/2020 21:11", "Bsn", "Celest", getString(R.string.six), 15, 5)
        n6.setImagess(R.drawable.av6)
        val n7 = News(
            "01/27/2020 10:17",
            "Messi",
            "Champion",
            getString(R.string.seven),
            195,
            5
        )
        n7.setImagess(R.drawable.av7)
        val n8 = News(
            "01/24/2020 04:32",
            "Lykeus_",
            "_______Help me Stay_______",
            getString(R.string.ei),
            177,
            55
        )
        n8.setImagess(R.drawable.av8)
        val n9 = News(
            "02/14/2020 06:34",
            "♛_\uD835\uDC40\uD835\uDCBE\uD835\uDCCF\uD835\uDCB6\uD835\uDCC3_♛",
            "A Glorious Mess She is",
            getString(R.string.ni),
            174,
            54
        )
        n9.setImagess(R.drawable.av9)
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

    fun removeLike(news: News?) {
        newsAdapter?.removeLike(news!!)
    }
}
