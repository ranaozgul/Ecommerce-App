package com.example.eticaretuyg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretuyg.databinding.ItemFavoriBinding

class FavoriAdapter(
    private val favoriList: MutableList<FavoriUrun>,
    private val onUrunClick:(FavoriUrun)-> Unit,
    private val onSepeteEkle: (SepetUrun) -> Unit,
    private val onFavoridenCikar: (FavoriUrun) -> Unit
) : RecyclerView.Adapter<FavoriAdapter.FavoriViewHolder>() {

    inner class FavoriViewHolder(val binding: ItemFavoriBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriViewHolder {
        val binding = ItemFavoriBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriViewHolder, position: Int) {
        val urun = favoriList[position]
        holder.binding.apply {
            favoriUrunIsim.text = urun.isim
            favoriUrunFiyat.text = "${urun.fiyat} ₺"
            favoriUrunResim.setImageResource(urun.resim)

            btnSepeteEkle.setOnClickListener { onSepeteEkle.invoke(urun.toSepetUrun()) }
            btnFavoridenCikar.setOnClickListener { onFavoridenCikar(urun) }

            root.setOnClickListener { onUrunClick.invoke(urun) }
        }

    }

    override fun getItemCount(): Int = favoriList.size

    fun updateList(newList: List<FavoriUrun>) {
        favoriList.clear()
        favoriList.addAll(newList)
        notifyDataSetChanged()
    }
}
