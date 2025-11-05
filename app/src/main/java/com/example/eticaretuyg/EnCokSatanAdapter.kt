package com.example.eticaretuyg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EnCokSatanAdapter(
    private val urunListesi:
    List<EnCokSatan>):
RecyclerView.Adapter<EnCokSatanAdapter.EnCokSatanViewHolder>() {

    inner class EnCokSatanViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        val imageViewUrun: ImageView = itemView.findViewById(R.id.imageViewUrun)
        val textViewUrunAdi: TextView = itemView.findViewById(R.id.textViewUrunAdi)
        val textViewUrunFiyat: TextView = itemView.findViewById(R.id.textViewUrunFiyat)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EnCokSatanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_en_cok_satan,parent,false)
        return EnCokSatanViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: EnCokSatanViewHolder,
        position: Int
    ) {
        val urun = urunListesi[position]
        holder.imageViewUrun.setImageResource(urun.resim)
        holder.textViewUrunAdi.text = urun.urunAdi
        holder.textViewUrunFiyat.text = urun.fiyat
    }

    override fun getItemCount(): Int = urunListesi.size




}