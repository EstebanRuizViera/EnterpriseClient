package com.example.enterpriseclient.myDataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.time.Instant
import java.time.OffsetDateTime


@Entity(tableName = Availability.TABLE_NAME)
data class Availability(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "created_at") @NotNull val created_at: Int,
    @ColumnInfo(name = "updated_at") @NotNull val updated_at: Int,
    @ColumnInfo(name = "price") @NotNull val price: Double,
    @ColumnInfo(name = "quota") @NotNull val quota: Double,
    @ColumnInfo(name = "id_product") @NotNull val id_product: Int
) {
    companion object {
        const val TABLE_NAME = "availability"
    }

}