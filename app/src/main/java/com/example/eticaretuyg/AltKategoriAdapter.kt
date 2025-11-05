package com.example.eticaretuyg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretuyg.AltKategoriDeposu.altKategoriler
import com.example.eticaretuyg.databinding.ItemAltKategoriBinding

class AltKategoriAdapter(
    private var altKategoriler: MutableList<AltKategori>,
    private val onAltKategoriClick: (AltKategori) -> Unit
) : RecyclerView.Adapter<AltKategoriAdapter.AltKategoriViewHolder>() {

    inner class AltKategoriViewHolder(val binding: ItemAltKategoriBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AltKategoriViewHolder {
        val binding = ItemAltKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AltKategoriViewHolder(binding)
    }

    override fun getItemCount(): Int = altKategoriler.size

    override fun onBindViewHolder(holder: AltKategoriViewHolder, position: Int) {
        val altKategori = altKategoriler[position]
        holder.binding.textViewAltKategoriIsim.text = altKategori.isim
        holder.binding.root.setOnClickListener {
            onAltKategoriClick(altKategori)
        }
    }

    fun updateList(newList: MutableList<AltKategori>) {
        altKategoriler.clear()
        altKategoriler.addAll(newList)
        notifyDataSetChanged()
    }
}

