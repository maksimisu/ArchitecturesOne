package com.maksimisu.architecturesone.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maksimisu.architecturesone.data.entities.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM table_persons")
    fun getData(): Flow<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM table_persons")
    suspend fun clear()

}