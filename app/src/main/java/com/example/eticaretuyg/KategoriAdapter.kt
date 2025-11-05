package com.example.eticaretuyg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretuyg.databinding.ItemKategoriBinding

class KategoriAdapter(
    private val kategoriler: List<Kategori>,
    private val onAltKategoriClick: (AltKategori) -> Unit
) : RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>() {

    inner class KategoriViewHolder(val binding: ItemKategoriBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val binding = ItemKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KategoriViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        val kategori = kategoriler[position]
        holder.binding.textViewKategoriAdi.text = kategori.isim

        // Alt kategorileri göster/gizle
        holder.binding.kategoriSatiri.setOnClickListener {
            kategori.acikMi = !kategori.acikMi
            holder.binding.recyclerAltKategoriler.removeAllViews()
            if (kategori.acikMi) {
                kategori.altKategoriler.forEach { altKategori ->
                    val textView = TextView(holder.binding.root.context).apply {
                        text = altKategori.isim
                        textSize = 16f
                        setPadding(20, 10, 10, 10)
                        setOnClickListener { onAltKategoriClick(altKategori) }
                    }
                    holder.binding.recyclerAltKategoriler.addView(textView)
                }
                holder.binding.recyclerAltKategoriler.visibility = View.VISIBLE
            } else {
                holder.binding.recyclerAltKategoriler.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = kategoriler.size
}
