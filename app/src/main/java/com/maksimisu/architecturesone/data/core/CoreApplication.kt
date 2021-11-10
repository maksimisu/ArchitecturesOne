package com.maksimisu.architecturesone.data.core

import android.app.Application
import com.maksimisu.architecturesone.data.db.PersonsDatabase
import com.maksimisu.architecturesone.ui.screens.main.PersonsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CoreApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy {
        PersonsDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        PersonsRepository(database.personDao())
    }

}