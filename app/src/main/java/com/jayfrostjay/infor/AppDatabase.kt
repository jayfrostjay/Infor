package com.jayfrostjay.infor

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jayfrostjay.infor.dao.ContactDao
import com.jayfrostjay.infor.data.Contacts

@Database(entities = arrayOf(Contacts::class), version = 8)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
