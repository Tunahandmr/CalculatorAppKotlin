package com.tunahan.calculatorappkotlin.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "calculator_table")
data class History(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "process")
    val process: String?,
    @ColumnInfo(name = "result")
    val result: String?
):Parcelable