package com.maksimisu.architecturesone.ui.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maksimisu.architecturesone.data.entities.Person
import com.maksimisu.architecturesone.databinding.ActivityMainBinding
import com.maksimisu.architecturesone.ui.screens.createnew.CreateNewActivity
import org.koin.android.ext.android.get

class MainActivity<T : MainPresenter<V>, V : MainView> : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var personsRVAdapter: PersonsRVAdapter

    private lateinit var presenter: T
    private fun providePresenter(): MainPresenter<V> = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        presenter = providePresenter() as T
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, CreateNewActivity::class.java))
        }

        binding.fabClear.setOnClickListener {
            presenter.clear()
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

    override fun showPersons(persons: List<Person>) {
        TODO("Not yet implemented")
    }
}