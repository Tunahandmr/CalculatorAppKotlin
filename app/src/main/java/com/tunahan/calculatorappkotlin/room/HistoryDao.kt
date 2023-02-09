package com.tunahan.calculatorappkotlin.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tunahan.calculatorappkotlin.model.History

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(history: History)

    @Delete
    suspend fun deleteHistory(history: History)

    @Query("SELECT * FROM calculator_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<History>>

}