package com.example.eticaretuyg

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "sepet")
data class SepetUrun(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val isim: String,
    val fiyat: Double,
    val resim: Int,
    var adet: Int,
    val altKategoriId: Int

): Parcelable