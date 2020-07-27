package com.bombadu.diffutilexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Name::class], version = 1, exportSchema = false)

abstract class NameDatabase : RoomDatabase() {

    abstract fun nameDao(): NameDao

    private class NameDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { nameDatabase ->
                scope.launch {

                }
            }
        }

    }



    companion object {
        @Volatile
        private var INSTANCE: NameDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NameDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    NameDatabase::class.java,
                    "record_database"
                ).addCallback(NameDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}

