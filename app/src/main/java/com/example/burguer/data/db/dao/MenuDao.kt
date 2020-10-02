package com.example.burguer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.burguer.data.db.MenuEntity

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(menu: MenuEntity)

    @Query("DELETE FROM menu")
    fun deleteAll()

    @Query("SELECT * FROM menu WHERE id = :id")
    fun getMenu(id: Long) : MenuEntity

}