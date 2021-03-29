package com.example.newsapp.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.newsapp.db.dao.NewsDao
import com.example.newsapp.db.entity.News

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        internal var instance: NewsDatabase? = null
        fun getInstance(context: Context): NewsDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    NewsDatabase::class.java,
                    "news_database"
                )
                    .addCallback(RoomCallback)
                    .build()
            }
            return instance as NewsDatabase
        }
    }
}

object RoomCallback : RoomDatabase.Callback() {
    private val noteDao = NewsDatabase.instance?.newsDao()
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        PopulateDbAsyncTask(noteDao).execute()
    }
}

class PopulateDbAsyncTask(newsDatabase: NewsDao?) : AsyncTask<Unit, Unit, Unit>() {
    private val noteDao = newsDatabase
    override fun doInBackground(vararg p0: Unit?) {
        noteDao?.insert(
            News(
                author = "",
                title = "",
                description = "",
                url = "",
                urlToImage = "",
                publishedAt = "",
                content = ""
            )
        )
    }

}
