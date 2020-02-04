package com.example.enterpriseclient.myDataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

@Entity(tableName = Reservation.TABLE_NAME)
data class Reservation(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "total") @NotNull val total: Float,
    @ColumnInfo(name = "status") @NotNull val status: String,
    @ColumnInfo(name = "date") @NotNull val date: LocalDate
) {
    companion object {
        const val TABLE_NAME = "reservation"
    }

}