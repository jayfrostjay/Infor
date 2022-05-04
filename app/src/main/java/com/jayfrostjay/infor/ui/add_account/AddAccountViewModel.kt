package com.jayfrostjay.infor.ui.add_account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase
import com.jayfrostjay.infor.AppDatabase
import com.jayfrostjay.infor.dao.ContactDao
import com.jayfrostjay.infor.data.Contacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddAccountViewModel(db: RoomDatabase): ViewModel() {

    val contactDao: ContactDao? = (db as? AppDatabase)?.contactDao()

    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean> = _saveSuccess

    fun saveToDatabase(contacts: Contacts){
        GlobalScope.launch(Dispatchers.Default){
            contactDao?.insertAll(contacts)

            GlobalScope.launch(Dispatchers.Main){
                _saveSuccess.postValue(true)
            }
        }
    }

    class Factory(val db: RoomDatabase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddAccountViewModel(db) as T
        }
    }
}