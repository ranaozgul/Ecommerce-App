package com.example.eticaretuyg

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "urunler")
data class Urun(
    @PrimaryKey(autoGenerate = true)
    val urunId: Int = 0,
    val urunAdi: String,
    val fiyat: Double,
    val resim: Int,
    val altKategoriId: Int,
    var isFavori: Boolean = false
)
