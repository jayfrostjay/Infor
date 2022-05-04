package com.jayfrostjay.infor.ui.add_account

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jayfrostjay.infor.AppDatabase
import com.jayfrostjay.infor.data.Contacts
import com.jayfrostjay.infor.databinding.ActivityAddContactBinding

class AddAccountActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private lateinit var viewModel: AddAccountViewModel
    private lateinit var db: RoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "infor"
        )
            .fallbackToDestructiveMigration()
            .build()

        viewModel = ViewModelProvider(this, AddAccountViewModel.Factory(db)).get(AddAccountViewModel::class.java)

        if(savedInstanceState == null){
            initObservers()
            initListeners()
        }
    }

    override fun onResume() {
        super.onResume()
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    fun initObservers(){
        viewModel.saveSuccess.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Save Success", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initListeners(){
        binding.save.setOnClickListener {
            viewModel.saveToDatabase(Contacts(
                firstname = binding.firstname.text.toString(),
                lastname = binding.lastname.text.toString(),
                numbers = binding.contactNumbers.text.toString()
            ))
        }
    }

    companion object {
        const val ID = "id"
    }
}