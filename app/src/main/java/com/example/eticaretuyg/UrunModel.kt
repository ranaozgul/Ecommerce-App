package com.example.eticaretuyg

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrunModel (
    val id: Int,
    val isim: String,
    val fiyat: Double,
    val resim: Int,
    val altKategoriId: Int,
    var isFavori: Boolean = false
): Parcelable {
    fun toFavoriUrun(): FavoriUrun {
        return FavoriUrun(
            id = id,
            isim = isim,
            fiyat = fiyat,
            resim = resim,
            altKategoriId = altKategoriId
        )
    }
}

