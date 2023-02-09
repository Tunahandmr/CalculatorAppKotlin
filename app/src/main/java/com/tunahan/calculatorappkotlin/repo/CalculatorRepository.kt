package com.tunahan.calculatorappkotlin.repo

import androidx.lifecycle.LiveData
import com.tunahan.calculatorappkotlin.model.History
import com.tunahan.calculatorappkotlin.room.HistoryDao

class CalculatorRepository(private val historyDao: HistoryDao) {

    val readAllData: LiveData<List<History>> = historyDao.readAllData()

    suspend fun addHistory(history: History){
        historyDao.addHistory(history)
    }

    suspend fun deleteHistory(history: History){
        historyDao.deleteHistory(history)
    }
}