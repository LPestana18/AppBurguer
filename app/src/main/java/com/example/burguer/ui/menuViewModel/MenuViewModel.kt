package com.example.burguer.ui.menuViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burguer.repository.MenuRepository
import kotlinx.coroutines.launch

class MenuViewModel( private val repository: MenuRepository) : ViewModel() {

    fun addMenu(name: String, description: String, price: Double)  = viewModelScope.launch {
        
    }
}