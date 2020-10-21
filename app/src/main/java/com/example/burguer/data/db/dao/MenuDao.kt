package com.example.burguer.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.burguer.data.db.entity.MenuEntity

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(menu: MenuEntity): Long

    @Update
    suspend fun update(menu: MenuEntity)

    @Query("DELETE FROM menu WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM menu")
    suspend fun deleteAll()

    @Query("SELECT * FROM menu WHERE id = :id")
    suspend fun getMenu(id: Long) : MenuEntity

    @Query("SELECT * FROM menu")
    fun getAll(): LiveData<List<MenuEntity>>

}