package com.github.jaydeepw.assignment01.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.github.jaydeepw.assignment01.Constants
import com.github.jaydeepw.assignment01.db.dao.AlbumDao
import com.github.jaydeepw.assignment01.models.dataclasses.Album


class AlbumRepository internal constructor(application: Application) {

    private val albumDao: AlbumDao
    internal val albums: LiveData<List<Album>>

    init {
        val db = Room.databaseBuilder(application,
            AppDatabase::class.java, Constants.Companion.DB_NAME).build()
        albumDao = db.albumDao()
        albums = albumDao.all
    }

    fun insertAll(list: List<Album>) {
        InsertAllAsyncTask(albumDao).execute(list)
    }

    private class InsertAllAsyncTask internal constructor(private val mAsyncTaskDao: AlbumDao) :
        AsyncTask<List<Album>, Void, Void>() {

        override fun doInBackground(vararg params: List<Album>): Void? {
            val list = params[0] as MutableList<Album>
            mAsyncTaskDao.deleteAll(list)
            mAsyncTaskDao.insertAll(list)
            return null
        }
    }
}