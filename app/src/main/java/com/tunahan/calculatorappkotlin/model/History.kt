package com.tunahan.calculatorappkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculator_table")
data class History(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "process")
    val process: String?,
    @ColumnInfo(name = "result")
    val result: String?
)