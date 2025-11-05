package com.example.eticaretuyg

import android.app.Application
import android.view.animation.Transformation
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SepetViewModel(application: Application):
AndroidViewModel(application){
    private val dao = UygDatabase.getDatabase(application).sepetDao()
    val sepetUrunleri: LiveData<List<SepetUrun>> = dao.getSepettekiler()
    val toplamFiyat =
        androidx.lifecycle.MediatorLiveData<Double>().apply {
            addSource(sepetUrunleri){uruns ->
                value = uruns.sumOf { urun -> urun.fiyat*urun.adet }
            }
        }
    fun sepeteEkle(urun: SepetUrun) {
        viewModelScope.launch {
            val result = dao.sepeteEkle(urun)
            if (result == -1L){
                dao.adetArttir(urun.id)
            }
        }
    }

    fun adetArttir(id: Int){
        viewModelScope.launch {
            dao.adetArttir(id)
        }
    }

    fun adetAzalt(id: Int){
        viewModelScope.launch {
            dao.adetAzalt(id)
        }
    }


    fun sepettenCikar(urun: SepetUrun){
        viewModelScope.launch {
            dao.sepettenCikar(urun)
        }
    }

    fun sepetiBosalt(){
        viewModelScope.launch {
            dao.sepetiBosalt()
        }
    }
}