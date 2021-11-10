package com.maksimisu.architecturesone.ui.screens.main

import androidx.lifecycle.*
import com.maksimisu.architecturesone.data.entities.Person
import kotlinx.coroutines.launch

class PersonViewModel(
    private val repository: PersonsRepository
) : ViewModel() {

    val persons: LiveData<List<Person>> = repository.persons.asLiveData()

    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }
}

class PersonViewModelFactory(
    private val repository: PersonsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            return PersonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}