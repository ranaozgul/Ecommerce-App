package com.example.eticaretuyg

import androidx.room.*

@Dao
interface UrunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun urunEkle(urun: Urun)

    @Update
    suspend fun urunGuncelle(urun: Urun)

    @Delete
    suspend fun urunSil(urun: Urun)

    @Query("SELECT * FROM urunler WHERE isFavori = 1")
    suspend fun favoriUrunler(): List<Urun>

    @Query("SELECT * FROM urunler")
    suspend fun tumUrunler(): List<Urun>

    @Query("SELECT * FROM urunler WHERE urunId = :id")
    suspend fun urunGetir(id: Int): Urun?

    @Query("SELECT * FROM urunler WHERE altKategoriId = :altKategoriId")
    suspend fun altKategoriUrunleriGetir(altKategoriId: Int): List<Urun>
}
