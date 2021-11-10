package com.maksimisu.architecturesone.ui.screens.main

import androidx.annotation.WorkerThread
import com.maksimisu.architecturesone.data.db.dao.PersonDao
import com.maksimisu.architecturesone.data.entities.Person
import kotlinx.coroutines.flow.Flow

class PersonsRepository(
    private val personDao: PersonDao
) {

    val persons: Flow<List<Person>> = personDao.getData()

    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.insert(person)
    }

    @WorkerThread
    suspend fun clear() {
        personDao.clear()
    }

}