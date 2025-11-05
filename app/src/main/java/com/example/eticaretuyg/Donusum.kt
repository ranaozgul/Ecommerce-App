package com.example.eticaretuyg
    fun Urun.toUrunModel() = UrunModel(
        id = this.urunId,
        isim = this.urunAdi,
        fiyat = this.fiyat,
        resim = this.resim,
        altKategoriId = this.altKategoriId,
        isFavori = this.isFavori
    )

    fun UrunModel.toUrunEntity() = Urun(
        urunId = this.id,
        urunAdi = this.isim,
        fiyat = this.fiyat,
        resim = this.resim,
        altKategoriId = this.altKategoriId,
        isFavori = this.isFavori
    )

fun UrunModel.toFavoriUrun(): FavoriUrun {
    return FavoriUrun(
        id = this.id,
        isim = this.isim,
        fiyat = this.fiyat,
        resim = this.resim,
        altKategoriId = this.altKategoriId
    )
}

fun UrunModel.toSepetUrun(): SepetUrun {
    return SepetUrun(
        id = this.id,
        isim = this.isim,
        fiyat = this.fiyat,
        resim = this.resim,
        altKategoriId = this.altKategoriId,
        adet = 1
    )
}

fun FavoriUrun.toSepetUrun(): SepetUrun{
    return SepetUrun(
        id = this.id,
        isim = this.isim,
        fiyat = this.fiyat,
        resim = this.resim,
        altKategoriId = this.altKategoriId,
        adet = 1
    )
}

