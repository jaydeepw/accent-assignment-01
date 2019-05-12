package com.github.jaydeepw.assignment01

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.jaydeepw.assignment01.db.AppDatabase
import com.github.jaydeepw.assignment01.db.dao.AlbumDao
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsDaoTest {
    lateinit var albumDao: AlbumDao
    lateinit var database: AppDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        albumDao = database.albumDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertedAndRetrievedUsersMatch() {
        val album1Expected = Album(1, "My Title", 100)
        val album2Expected = Album()
        val users = mutableListOf(album1Expected, album2Expected)
        albumDao.deleteAll()
        albumDao.insertAll(users)

        val allUsers = albumDao.all
        val album1Actual = allUsers[0]
        Assert.assertEquals(album1Actual, album1Expected)
    }

    @Test
    fun testInsertedAndRetrievedUsersCountMatch() {
        val album1Expected = Album(1, "My Title", 100)
        val album2Expected = Album()
        val users = mutableListOf(album1Expected, album2Expected)
        albumDao.deleteAll()
        albumDao.insertAll(users)

        val allUsers = albumDao.all
        Assert.assertEquals(allUsers.size, users.size)
    }

    @Test
    fun testSingleRowDataIntegrity() {
        val album1Expected = Album(21, "My Title", 100)
        albumDao.deleteAll()
        albumDao.insert(album1Expected)

        val allUsers = albumDao.all
        val album1Actual = allUsers[0]
        Assert.assertEquals(album1Actual.id, album1Expected.id)
        Assert.assertEquals(album1Actual.title, album1Expected.title)
        Assert.assertEquals(album1Actual.userId, album1Expected.userId)
    }
}