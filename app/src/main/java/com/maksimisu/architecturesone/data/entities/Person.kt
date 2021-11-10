package com.maksimisu.architecturesone.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_persons")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val firstName: String,
    val lastName: String,
)