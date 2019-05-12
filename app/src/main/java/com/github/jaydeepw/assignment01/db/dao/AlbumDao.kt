package com.github.jaydeepw.assignment01.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.jaydeepw.assignment01.models.dataclasses.Album

@Dao
interface AlbumDao {
    @get:Query("SELECT * FROM albums")
    val all: List<Album>

    @Insert
    fun insertAll(albums: MutableList<Album>)

    @Insert
    fun insert(albums: Album)

    @Delete
    fun delete(album: Album)

    @Delete
    fun deleteAll(albums: MutableList<Album>)
}