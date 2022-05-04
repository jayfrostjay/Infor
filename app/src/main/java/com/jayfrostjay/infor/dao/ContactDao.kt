package com.jayfrostjay.infor.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jayfrostjay.infor.data.Contacts

@Dao
interface ContactDao {
    @get:Query("SELECT * FROM contacts")
    val all: List<Contacts?>?

    @Query("SELECT * FROM contacts WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray?): List<Contacts?>?

    @Insert
    fun insertAll(vararg contact: Contacts?)

    @Delete
    fun delete(contact: Contacts?)
}
