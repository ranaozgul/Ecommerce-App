package com.example.eticaretuyg

data class Kategori (
    val id: Int,
    val isim: String,
    val altKategoriler: List<AltKategori>,
    var acikMi: Boolean = false
)
