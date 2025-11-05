package com.example.eticaretuyg

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriUrunDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun favoriyeEkle(favoriUrun: FavoriUrun)

    @Delete
    suspend fun favoridenCikar(favoriUrun: FavoriUrun)

    @Query("SELECT * FROM favori_urunler")
    fun tumFavoriler(): LiveData<List<FavoriUrun>>

    @Query("SELECT EXISTS(SELECT 1 FROM favori_urunler WHERE id = :id)")
    suspend fun urunFavorideMi(id: Int): Boolean
}
