package com.example.burguer.repository

import androidx.lifecycle.LiveData
import com.example.burguer.data.db.entity.MenuEntity

interface MenuRepository {

    suspend fun insertMenu(name: String, description: String, price: Double): Long

    suspend fun updateMenu(id: Long, name: String, description: String, price: Double)

    suspend fun deleteMenu(id: Long)

    suspend fun deleteAllMenu()

    suspend fun getAllMenu(): LiveData<List<MenuEntity>>
}