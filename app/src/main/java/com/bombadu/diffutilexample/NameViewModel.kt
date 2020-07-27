package com.bombadu.diffutilexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

class NameViewModel(application: Application) : AndroidViewModel(application){

    private val repository: NameRepository
    private val allNames: LiveData<List<Name>>

    init {
        val nameDao = NameDatabase.getDatabase(application, viewModelScope).nameDao()
        repository = NameRepository(nameDao)
        allNames = repository.allNames

    }


    fun insertName(name: Name) {
        repository.insertName(name)
    }

    fun deleteName(name: Name) {
        repository.deleteName(name)
    }

    fun getAllNames(): LiveData<List<Name>> {
        return  allNames
    }

    fun deleteAllNames() {
        repository.deleteAllNames()
    }
}