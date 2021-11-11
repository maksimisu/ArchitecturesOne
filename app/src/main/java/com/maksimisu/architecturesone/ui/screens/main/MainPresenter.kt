package com.maksimisu.architecturesone.ui.screens.main

import android.util.Log
import com.maksimisu.architecturesone.data.entities.Person
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class MainPresenter<V : MainView> {

    private val job: Job = SupervisorJob()

    private val scope: CoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + job +
                    CoroutineExceptionHandler { _, throwable ->
                        onError(throwable)
                    }
    }

    fun onError(e: Throwable) {
        Log.e("Error", e.message, e)
    }

    abstract fun addPerson(id: Int, firstName: String, lastName: String)

    abstract fun getData(): List<Person>

    abstract fun clear()
}