package com.example.eticaretuyg

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarkaPagerAdapter(
    private val context: Context,
    private val sayfaListesi: List<List<Marka>>
) : RecyclerView.Adapter<MarkaPagerAdapter.MarkaSayfaViewHolder>() {

    inner class MarkaSayfaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerViewMarka: RecyclerView = itemView.findViewById(R.id.recyclerGridMarka
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkaSayfaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_marka_page, parent, false)
        return MarkaSayfaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarkaSayfaViewHolder, position: Int) {
        val markaListesi = sayfaListesi[position]
        val adapter = MarkaGridAdapter(markaListesi)
        holder.recyclerViewMarka.layoutManager = GridLayoutManager(context, 3) // 3 sütun
        holder.recyclerViewMarka.adapter = adapter
    }

    override fun getItemCount(): Int {
        return sayfaListesi.size
    }
}


