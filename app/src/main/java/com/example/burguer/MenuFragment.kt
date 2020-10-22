package com.example.burguer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.burguer.data.db.AppDatabase
import com.example.burguer.data.db.dao.MenuDao
import com.example.burguer.extension.hideKeyboard
import com.example.burguer.repository.DatabaseDataSource
import com.example.burguer.repository.MenuRepository
import com.example.burguer.ui.menuViewModel.MenuViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewModel: MenuViewModel by viewModels {
        object : ViewModelProvider.Factory{

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val menuDao: MenuDao = AppDatabase.getDatabase(requireContext()).menudao

                val repository: MenuRepository = DatabaseDataSource(menuDao)
                return MenuViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.menuStateEventData.observe(viewLifecycleOwner) { menuState ->
            when (menuState) {
                is MenuViewModel.MenuState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) {stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        input_name.text?.clear()
        input_description.text?.clear()
        input_price.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_menu.setOnClickListener {
            val name = input_name.text.toString()
            val description = input_description.text.toString()
            val price = input_price.text.toString().toDouble()

            viewModel.addMenu(name, description, price)
        }
    }
}