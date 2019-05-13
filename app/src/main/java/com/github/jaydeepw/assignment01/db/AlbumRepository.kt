package com.github.jaydeepw.assignment01.db

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.room.Room
import com.github.jaydeepw.assignment01.Constants
import com.github.jaydeepw.assignment01.R
import com.github.jaydeepw.assignment01.db.dao.AlbumDao
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.models.datasource.AlbumsCallback
import com.github.jaydeepw.assignment01.models.datasource.network.MainNetworkModel
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class AlbumRepository internal constructor(application: Application) {

    private val albumDao: AlbumDao

    @Inject
    lateinit var mainModel: MainNetworkModel

    init {
        val db = Room.databaseBuilder(application,
            AppDatabase::class.java, Constants.Companion.DB_NAME).build()
        albumDao = db.albumDao()
    }

    private fun insertAll(list: List<Album>) {
        InsertAllAsyncTask(albumDao).execute(list)
    }

    fun getAll(callback: AlbumsCallback) {
        RetrieveAllAsyncTask(albumDao, callback).execute()

        // update the DB
        mainModel.getData(object : AlbumsCallback {
            override fun onSuccess(list: MutableList<Album>) {
                Log.d("MainPresenter", "network.list.size ${list.size}")
                insertAll(list)

                // notify the subscriber about this event.
                // in our case, it will be the UI.
                EventBus.getDefault().post(list)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onNotSuccess(messageResId: Int) {
                callback.onNotSuccess(messageResId)
            }
        })
    }

    private class InsertAllAsyncTask internal constructor(private val albumDao: AlbumDao) :
        AsyncTask<List<Album>, Void, Void>() {

        override fun doInBackground(vararg params: List<Album>): Void? {
            val list = params[0] as MutableList<Album>
            albumDao.deleteAll()
            albumDao.insertAll(list)
            return null
        }
    }

    private class RetrieveAllAsyncTask internal constructor(
        private val asyncTaskDao: AlbumDao,
        private val callback: AlbumsCallback
    ) :
        AsyncTask<AlbumsCallback, Void, List<Album>>() {
        override fun doInBackground(vararg params: AlbumsCallback): List<Album>? {
            return asyncTaskDao.all
        }

        override fun onPostExecute(result: List<Album>?) {
            super.onPostExecute(result)

            try {
                if (result != null) {
                    callback.onSuccess(result as MutableList<Album>)
                } else {
                    callback.onNotSuccess(R.string.msg_failure_get_albums_db)
                }
            } catch (e: Exception) {
                callback.onFailure(e.message ?: e.localizedMessage)
            }
        }
    }
}