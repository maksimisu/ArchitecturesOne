package com.maksimisu.architecturesone.ui.screens.createnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.maksimisu.architecturesone.data.core.CoreApplication
import com.maksimisu.architecturesone.data.entities.Person
import com.maksimisu.architecturesone.databinding.ActivityCreateNewBinding
import com.maksimisu.architecturesone.ui.screens.main.PersonViewModel
import com.maksimisu.architecturesone.ui.screens.main.PersonViewModelFactory

class CreateNewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNewBinding

    private val personViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as CoreApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        binding.btnAdd.setOnClickListener {
            var id = 0
            personViewModel.persons.observe(this) { persons ->
                id = persons.size
            }
            val firstname = binding.etFirstname.text.toString()
            val lastname = binding.etLastname.text.toString()
            personViewModel.insert(Person(id, firstname, lastname))

            finish()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun init() {
        binding = ActivityCreateNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}