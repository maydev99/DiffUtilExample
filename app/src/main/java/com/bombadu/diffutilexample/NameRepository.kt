package com.bombadu.diffutilexample

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NameRepository (private val nameDao: NameDao) {

    val allNames: LiveData<List<Name>> = nameDao.getNames()

    fun insertName(name: Name) {
        InsertNameAsyncTask(
            nameDao
        ).execute(name)
    }

    fun deleteName(name: Name) {
        DeleteNameAsyncTask(
            nameDao
        ).execute(name)
    }

    fun deleteAllNames() {
        DeleteAllNamesAsyncTask(
            nameDao
        ).execute()
    }

    private class InsertNameAsyncTask(val nameDao: NameDao) : AsyncTask<Name, Unit, Unit>() {
        override fun doInBackground(vararg name: Name?) {
            nameDao.insertName(name[0]!!)
        }

    }

    private class DeleteNameAsyncTask(val nameDao: NameDao) : AsyncTask<Name, Unit, Unit>() {
        override fun doInBackground(vararg name: Name?) {
            nameDao.deleteName(name[0]!!)
        }

    }

    private class DeleteAllNamesAsyncTask(val nameDao: NameDao) : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0: Unit?) {
            nameDao.deleteAllNames()
        }
    }


}