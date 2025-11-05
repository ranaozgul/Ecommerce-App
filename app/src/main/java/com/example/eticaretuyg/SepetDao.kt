package com.example.eticaretuyg

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SepetDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun sepeteEkle(urun: SepetUrun): Long

    @Query("UPDATE sepet SET adet = adet + 1 WHERE id = :id")
    suspend fun adetArttir(id: Int): Int

    @Query("UPDATE sepet SET adet = adet - 1 WHERE id = :id AND adet > 1")
    suspend fun adetAzalt(id: Int): Int

    @Delete
    suspend fun sepettenCikar(urun: SepetUrun)

    @Query("SELECT*FROM sepet")
    fun getSepettekiler(): LiveData<List<SepetUrun>>

    @Query("DELETE FROM sepet")
    suspend fun sepetiBosalt()
}