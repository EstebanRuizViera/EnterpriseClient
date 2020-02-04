package com.example.enterpriseclient.myDataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Product.TABLE_NAME)
data class Product(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") @NotNull val name: String,
    @ColumnInfo(name = "description") @NotNull val description: String,
    @ColumnInfo(name = "id_distribution") @NotNull val id_distribution: Int
) {
    companion object {
        const val TABLE_NAME = "product"
    }

}