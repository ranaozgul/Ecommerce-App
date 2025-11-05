package com.example.eticaretuyg

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favori_urunler")
@Parcelize
data class FavoriUrun(
    @PrimaryKey val id: Int,
    val isim: String,
    val fiyat: Double,
    val resim: Int,
    val altKategoriId: Int

) : Parcelable {
    fun toUrunModel(): UrunModel {
        return UrunModel(
            id = id,
            isim = isim,
            fiyat = fiyat,
            resim = resim,
            altKategoriId = 0,
            isFavori = true
        )
    }
}
