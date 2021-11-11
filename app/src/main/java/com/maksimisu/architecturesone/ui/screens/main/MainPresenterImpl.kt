package com.maksimisu.architecturesone.ui.screens.main

import com.maksimisu.architecturesone.data.db.dao.PersonDao
import com.maksimisu.architecturesone.data.entities.Person
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainPresenterImpl(private val dao: PersonDao) : MainPresenter<MainView>() {

    override fun addPerson(id: Int, firstName: String, lastName: String) {
        GlobalScope.launch {
            dao.insert(Person(id, firstName, lastName))
        }
    }

    override fun getData(): List<Person> {
        var persons: List<Person> = listOf()
        dao.getData().map { persons = it }
        return persons
    }

    override fun clear() {
        GlobalScope.launch {
            dao.clear()
        }
    }
}