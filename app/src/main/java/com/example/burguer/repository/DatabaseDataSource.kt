package com.example.burguer.repository

import androidx.lifecycle.LiveData
import com.example.burguer.data.db.dao.MenuDao
import com.example.burguer.data.db.entity.MenuEntity

class DatabaseDataSource(private val menuDao: MenuDao) : MenuRepository {

    override suspend fun insertMenu(name: String, description: String, price: Double): Long {
        val menu = MenuEntity(name = name, description = description, price = price)
         return menuDao.insert(menu)
    }

    override suspend fun updateMenu(id: Long,name: String, description: String, price: Double) {
        val menu = MenuEntity(id= id, name= name, description= description, price=price)

        menuDao.update(menu)
    }

    override suspend fun deleteMenu(id: Long) {
        menuDao.delete(id)
    }

    override suspend fun deleteAllMenu() {
        menuDao.deleteAll()
    }

    override suspend fun getAllMenu(): LiveData<List<MenuEntity>> {
        return menuDao.getAll()
    }
}