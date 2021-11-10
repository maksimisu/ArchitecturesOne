package com.maksimisu.architecturesone.ui.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maksimisu.architecturesone.data.core.CoreApplication
import com.maksimisu.architecturesone.databinding.ActivityMainBinding
import com.maksimisu.architecturesone.ui.screens.createnew.CreateNewActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var personsRVAdapter: PersonsRVAdapter

    private val personViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as CoreApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        personViewModel.persons.observe(this) { persons ->
            persons.let { personsRVAdapter.persons = it }
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, CreateNewActivity::class.java))
        }

        binding.fabClear.setOnClickListener {
            personViewModel.clear()
        }
    }

    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvMain.apply {
            personsRVAdapter = PersonsRVAdapter()
            adapter = personsRVAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

        personsRVAdapter.setOnItemClickListener(object :
            PersonsRVAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
            }
        })
    }
}