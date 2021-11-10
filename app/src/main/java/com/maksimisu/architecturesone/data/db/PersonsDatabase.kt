package com.maksimisu.architecturesone.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.maksimisu.architecturesone.data.db.dao.PersonDao
import com.maksimisu.architecturesone.data.entities.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Person::class],
    version = 1,
    exportSchema = false
)
abstract class PersonsDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    private class PersonsDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val personDao = database.personDao()
                    personDao.clear()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PersonsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PersonsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonsDatabase::class.java,
                    "persons_database"
                )
                    .addCallback(PersonsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}