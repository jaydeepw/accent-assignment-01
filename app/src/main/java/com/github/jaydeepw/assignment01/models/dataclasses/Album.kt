package com.github.jaydeepw.assignment01.models.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val title: String = "",
        val userId: Int = 0
) {

}