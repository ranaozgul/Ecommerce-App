package com.example.eticaretuyg

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: FavoriUrunDao = UygDatabase.getDatabase(application).favoriUrunDao()

    // Favoriler LiveData
    val favoriler: LiveData<List<FavoriUrun>> = dao.tumFavoriler()

    // Sepet LiveData
    private val _sepet = androidx.lifecycle.MutableLiveData<MutableList<SepetUrun>>(mutableListOf())
    val sepet: LiveData<MutableList<SepetUrun>> = _sepet

    // Favoriye ekleme
    fun favoriyeEkle(urun: FavoriUrun) {
        viewModelScope.launch {
            dao.favoriyeEkle(urun)
        }
    }

    // Favoriden çıkarma
    fun favoridenCikar(urun: FavoriUrun) {
        viewModelScope.launch {
            dao.favoridenCikar(urun)
        }
    }

    // Ürün sepete ekle
    fun sepeteEkle(urun: SepetUrun) {
        val current = _sepet.value ?: mutableListOf()
        val exist = current.find { it.id == urun.id }
        if (exist != null) {
            exist.adet += urun.adet
        } else {
            current.add(urun)
        }
        _sepet.value = current
    }

    // Sepet ürününü güncelle (adet değiştir)
    fun updateSepetUrun(urun: SepetUrun) {
        val current = _sepet.value ?: mutableListOf()
        val index = current.indexOfFirst { it.id == urun.id }
        if (index != -1) {
            current[index] = urun
            _sepet.value = current
        }
    }

    // Sepetten ürün sil
    fun sepettenSil(urun: SepetUrun) {
        val current = _sepet.value ?: mutableListOf()
        current.removeAll { it.id == urun.id }
        _sepet.value = current
    }

    // Sepetteki toplam fiyat
    fun toplamFiyat(): Double {
        val current = _sepet.value ?: mutableListOf()
        return current.sumOf { it.fiyat * it.adet }
    }

    // Favoride mi kontrol (opsiyonel)
    suspend fun urunFavorideMi(id: Int): Boolean {
        return dao.urunFavorideMi(id)
    }
}
