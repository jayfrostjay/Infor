package com.jayfrostjay.infor.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jayfrostjay.infor.AppDatabase
import com.jayfrostjay.infor.databinding.ActivityMainBinding
import com.jayfrostjay.infor.ui.add_account.AddAccountActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var db: RoomDatabase

    private var adapter: MainActivityAdapter = MainActivityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "infor"
        )
            .fallbackToDestructiveMigration()
            .build()

        viewModel = ViewModelProvider(this, MainActivityViewModel.Factory(db)).get(MainActivityViewModel::class.java)

        if(savedInstanceState == null){
            initObservers()
            initView()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getContacts()
    }

    private fun initView(){
        binding.listing.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = this@MainActivity.adapter.also {
                it.onClick = { contact ->

                }
            }
        }

        binding.addContact.setOnClickListener {
            this@MainActivity.startActivity(
                Intent(this@MainActivity, AddAccountActivity::class.java)
            )
        }
    }

    private fun initObservers(){
        viewModel.list.observe(this, Observer {
            this@MainActivity.adapter.apply {
                list = it
                notifyDataSetChanged()
            }
        })
    }
}