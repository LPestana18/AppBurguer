package com.example.burguer.ui.menuViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burguer.R
import com.example.burguer.repository.MenuRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MenuViewModel( private val repository: MenuRepository) : ViewModel() {

    private val _menuStateEventData = MutableLiveData<MenuState>()
    val menuStateEventData: LiveData<MenuState>
        get() = _menuStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addMenu(name: String, description: String, price: Double)  = viewModelScope.launch {
        try {
            val id = repository.insertMenu(name, description, price)
            if (id > 0) {
                _menuStateEventData.value = MenuState.Inserted
                _messageEventData.value = R.string.inserted_successfully
            }
        }catch (ex: Exception) {
            _messageEventData.value = R.string.inserted_error_to_insert
            Log.e(TAG, ex.toString())
        }
    }

    sealed class MenuState {
        object Inserted : MenuState()
    }

    companion object {
        private val TAG = MenuViewModel::class.java.simpleName
    }
}