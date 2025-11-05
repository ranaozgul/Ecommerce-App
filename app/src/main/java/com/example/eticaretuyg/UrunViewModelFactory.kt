package com.example.eticaretuyg

import UrunViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UrunViewModelFactory(private val urunDao: UrunDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UrunViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UrunViewModel(urunDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
