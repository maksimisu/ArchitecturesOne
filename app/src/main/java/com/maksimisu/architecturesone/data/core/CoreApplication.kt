package com.maksimisu.architecturesone.data.core

import android.app.Application
import com.maksimisu.architecturesone.data.db.PersonsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CoreApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy {
        PersonsDatabase.getDatabase(this, applicationScope)
    }

}