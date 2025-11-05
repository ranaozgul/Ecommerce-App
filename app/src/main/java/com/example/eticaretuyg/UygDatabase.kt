package com.example.eticaretuyg

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Urun::class, FavoriUrun::class, SepetUrun::class], version = 4)
abstract class UygDatabase : RoomDatabase() {

    abstract fun urunDao(): UrunDao
    abstract fun favoriUrunDao(): FavoriUrunDao
    abstract fun sepetDao(): SepetDao

    companion object {
        @Volatile
        private var INSTANCE: UygDatabase? = null

        fun getDatabase(context: Context): UygDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UygDatabase::class.java,
                    "eticaret_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
