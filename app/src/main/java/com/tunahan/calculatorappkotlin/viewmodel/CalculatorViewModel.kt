package com.tunahan.calculatorappkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahan.calculatorappkotlin.model.History
import com.tunahan.calculatorappkotlin.repo.CalculatorRepository
import com.tunahan.calculatorappkotlin.room.HistoryDao
import com.tunahan.calculatorappkotlin.room.HistoryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<History>>
    private val repository:CalculatorRepository

    init {
        val hDao = HistoryDatabase.getDatabase(application).historyDao()
        repository = CalculatorRepository(hDao)
        readAllData = repository.readAllData
    }

    fun addHistory(history: History){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHistory(history)
        }
    }

    fun deleteHistory(history: History){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHistory(history)
        }
    }


}