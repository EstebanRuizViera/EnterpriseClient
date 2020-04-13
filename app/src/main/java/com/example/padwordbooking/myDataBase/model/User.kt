package com.example.padwordbooking.myDataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = User.TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "id_remoto") @NotNull val id_remoto: String,
    @ColumnInfo(name = "name") @NotNull val name: String,
    @ColumnInfo(name = "email") @NotNull val email: String,
    @ColumnInfo(name = "token") @NotNull val token: String
) {
    companion object {
        const val TABLE_NAME = "user"
    }

}