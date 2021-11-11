package com.maksimisu.architecturesone.ui.screens.main

import com.maksimisu.architecturesone.data.entities.Person

interface MainView {

    fun showPersons(persons: List<Person>)

}