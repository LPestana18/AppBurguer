package com.example.burguer.data.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.burguer.data.db.dao.MenuDao
import com.example.burguer.data.db.entity.MenuEntity

// Classe para o banco de dados

@Database(entities = [MenuEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract val menudao: MenuDao
    
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}