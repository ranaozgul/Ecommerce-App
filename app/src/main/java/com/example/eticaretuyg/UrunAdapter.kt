package com.example.eticaretuyg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eticaretuyg.databinding.ItemUrunBinding

class UrunAdapter(
    private var urunList: MutableList<UrunModel>,
    private val onUrunClick: (UrunModel) -> Unit,
    private val onFavoriClick: (UrunModel) -> Unit,
    private val onFavoriCikar: (UrunModel) -> Unit
) : RecyclerView.Adapter<UrunAdapter.UrunHolder>() {

    inner class UrunHolder(val binding: ItemUrunBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrunHolder {
        val binding = ItemUrunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UrunHolder(binding)
    }

    override fun onBindViewHolder(holder: UrunHolder, position: Int) {
        val urun = urunList[position]
        holder.binding.textViewUrunIsim.text = urun.isim
        holder.binding.textViewUrunFiyat.text = "${urun.fiyat} ₺"
        Glide.with(holder.binding.root).load(urun.resim).into(holder.binding.imageViewUrunResim)

        holder.binding.favoriButton.setImageResource(
            if (urun.isFavori) R.drawable.ic_favorite else R.drawable.bosfavorite
        )

        holder.binding.favoriButton.setOnClickListener {
            urun.isFavori = !urun.isFavori
            holder.binding.favoriButton.setImageResource(
                if (urun.isFavori) R.drawable.ic_favorite else R.drawable.bosfavorite
            )
            if (urun.isFavori) onFavoriClick(urun)
            else onFavoriCikar(urun)
        }

        holder.binding.root.setOnClickListener {
            onUrunClick(urun)
        }
    }

    override fun getItemCount(): Int = urunList.size

    fun updateList(newList: MutableList<UrunModel>) {
        urunList = newList
        notifyDataSetChanged()
    }
}
