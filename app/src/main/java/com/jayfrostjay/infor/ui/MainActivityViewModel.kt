package com.jayfrostjay.infor.ui

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

class MainActivityViewModel(private val roomDatabase: RoomDatabase): ViewModel() {

    val contactDao: ContactDao? = (roomDatabase as? AppDatabase)?.contactDao()

    private val _list = MutableLiveData<List<Contacts?>>()
    val list: LiveData<List<Contacts?>> = _list

    fun getContacts(){
        GlobalScope.launch(Dispatchers.Default){
            contactDao?.all?.let{
                GlobalScope.launch(Dispatchers.Main){
                    _list.postValue(it)
                }
            }
        }

    }

    class Factory(private val roomDatabase: RoomDatabase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(roomDatabase) as T
        }
    }
}