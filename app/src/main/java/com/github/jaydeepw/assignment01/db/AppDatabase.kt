package com.github.jaydeepw.assignment01.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.jaydeepw.assignment01.db.dao.AlbumDao
import com.github.jaydeepw.assignment01.models.dataclasses.Album

@Database(entities = [Album::class], version = 1)
//@TypeConverters(CarConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}