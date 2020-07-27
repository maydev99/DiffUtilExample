package com.bombadu.diffutilexample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NameDao {
    @Insert
    fun insertName(name: Name)

    @Delete
    fun deleteName(name: Name)

    @Query("SELECT * FROM name_table")
    fun getNames(): LiveData<List<Name>>

    @Query("DELETE FROM name_table")
    fun deleteAllNames()

}