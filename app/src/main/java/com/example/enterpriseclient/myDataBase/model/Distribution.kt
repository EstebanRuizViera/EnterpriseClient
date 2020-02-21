package com.example.enterpriseclient.myDataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.sql.Time
import java.time.LocalDate

@Entity(tableName = Distribution.TABLE_NAME)
data class Distribution(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "unit") @NotNull val unit: String,
    @ColumnInfo(name = "duration") @NotNull val duration: Int,
    @ColumnInfo(name = "time_start") @NotNull val time_start: Int,
    @ColumnInfo(name = "time_finish") @NotNull val time_finish: Int,
    @ColumnInfo(name = "block") @NotNull val block: Int
) {
    companion object {
        const val TABLE_NAME = "distribution"
    }

}