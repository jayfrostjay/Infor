package com.jayfrostjay.infor.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contacts(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") var firstname: String,
    @ColumnInfo(name = "last_name") var lastname: String,
    @ColumnInfo(name = "numbers") var numbers: String
)
