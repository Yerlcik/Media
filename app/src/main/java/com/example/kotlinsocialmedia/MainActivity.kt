package com.example.kotlinsocialmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var pager: ViewPager? = null
    private var pagerAdapter: PagerAdapter? = null
    var f1: Fragment = NewsList()
    var f2: Fragment = SavesList()
    var list: MutableList<Fragment> =
        ArrayList()
    var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bot)
        pager = findViewById(R.id.pager)
        list.add(f1)
        list.add(f2)
        pagerAdapter = SlidePagerAdapter(supportFragmentManager, list)
        pager?.adapter = pagerAdapter
        bottomNavigationView?.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_home -> {
                        pager?.setCurrentItem(0, false)
                        bottomNavigationView?.getMenu()?.findItem(R.id.nav_home)?.isChecked = true
                        bottomNavigationView?.getMenu()?.findItem(R.id.nav_home)
                            ?.setIcon(R.drawable.ic_home_black_24dp)
                    }
                    R.id.nav_book -> {
                        pager?.setCurrentItem(1, false)
                        bottomNavigationView?.getMenu()?.findItem(R.id.nav_book)?.isChecked = true
                    }
                }
                false
            })
    }


    fun myClick(news: News, option: Int) {
        val fragment =
            supportFragmentManager.findFragmentById(R.id.pager)
        if (option == 1)
            (fragment as SavesList?)?.saveNews(news) else (fragment as SavesList?)?.removeNews(news)
    }

    fun removeItemLike(news: News?) {
        (f1 as NewsList).removeLike(news)
        (f2 as SavesList).removeLike(news)
    }
}