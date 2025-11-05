import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eticaretuyg.Urun
import com.example.eticaretuyg.UrunDao
import com.example.eticaretuyg.UrunDeposu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UrunViewModel(private val urunDao: UrunDao) : ViewModel() {
    private val _favoriUrunlerLiveData = MutableLiveData<List<Urun>>()
    val favoriUrunlerLiveData: LiveData<List<Urun>> get() = _favoriUrunlerLiveData

    private val _tumUrunlerLiveData = MutableLiveData<List<Urun>>()
    val tumUrunlerLiveData: LiveData<List<Urun>> get() = _tumUrunlerLiveData


    init {
        urunleriYukle()
        favoriUrunleriYukle()
        _tumUrunlerLiveData.value = UrunDeposu.tumUrunler
    }

    private fun urunleriYukle() {
        viewModelScope.launch(Dispatchers.IO) {
            val liste = urunDao.tumUrunler()
            _tumUrunlerLiveData.postValue(liste)
        }
    }
    private fun favoriUrunleriYukle() {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriler = urunDao.favoriUrunler()
            _favoriUrunlerLiveData.postValue(favoriler)
        }
    }
    fun urunFavoriDurumuGuncelle(urun: Urun) {
        viewModelScope.launch {
            urun.isFavori = !urun.isFavori
            urunDao.urunGuncelle(urun)
            urunleriYukle()
            favoriUrunleriYukle()
        }
    }



}
