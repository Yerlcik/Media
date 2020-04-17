package com.example.kotlinsocialmedia

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class News : Parcelable {
    var date: String?
    var author: String?
    var title: String?
    var mainText: String?
    private var isLiked = false
    var likesCount: Int
    var images = 0
    var commentsCount: Int

    constructor(
        date: String?,
        author: String?,
        title: String?,
        main: String?,
        likesCount: Int,
        commentsCount: Int
    ) {
        this.date = date
        this.author = author
        this.title = title
        mainText = main
        this.likesCount = likesCount
        this.commentsCount = commentsCount
    }

    fun Liked(): Boolean {
        return isLiked
    }

    fun setLike(like: Boolean) {
        isLiked = like
    }

    fun like() {
        likesCount = likesCount + 1
    }

    fun setLikesCountt(likesCount: Int) {
        this.likesCount = likesCount
    }

    fun setImagess(images: Int) {
        this.images = images
    }


    override fun toString(): String {
        val sb = StringBuilder("News{")
        sb.append("date='").append(date).append('\'')
        sb.append(", author='").append(author).append('\'')
        sb.append(", title='").append(title).append('\'')
        sb.append(", main='").append(mainText).append('\'')
        sb.append(", likesCount=").append(likesCount)
        sb.append(", commentsCount=").append(commentsCount)
        sb.append('}')
        return sb.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(date)
        dest.writeString(author)
        dest.writeString(title)
        dest.writeString(mainText)
        dest.writeInt(likesCount)
        dest.writeInt(images)
        dest.writeInt(commentsCount)
    }

    protected constructor(`in`: Parcel) {
        date = `in`.readString()
        author = `in`.readString()
        title = `in`.readString()
        mainText = `in`.readString()
        likesCount = `in`.readInt()
        images = `in`.readInt()
        commentsCount = `in`.readInt()
    }

//    companion object {
//        var newsList: List<News> = ArrayList()
//        var database: List<News> = ArrayList()
//        val CREATOR: Parcelable.Creator<News> = object : Parcelable.Creator<News?> {
//            override fun createFromParcel(source: Parcel): News? {
//                return News(source)
//            }
//
//            override fun newArray(size: Int): Array<News?> {
//                return arrayOfNulls(size)
//            }
//        }
//    }

    companion object CREATOR : Parcelable.Creator<News> {
        var newsList: List<News> = ArrayList()
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}