package com.maksimisu.architecturesone.ui.screens.createnew

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maksimisu.architecturesone.databinding.ActivityCreateNewBinding
import com.maksimisu.architecturesone.ui.screens.main.MainPresenter
import com.maksimisu.architecturesone.ui.screens.main.MainView
import org.koin.android.ext.android.get

class CreateNewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNewBinding

    private lateinit var presenter: MainPresenter<MainView>
    private fun providePresenter(): MainPresenter<MainView> = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        presenter = providePresenter()

        binding.btnAdd.setOnClickListener {
            val id = presenter.getData()[presenter.getData().size].id
            val firstname = binding.etFirstname.text.toString()
            val lastname = binding.etLastname.text.toString()
            presenter.addPerson(id, firstname, lastname)
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