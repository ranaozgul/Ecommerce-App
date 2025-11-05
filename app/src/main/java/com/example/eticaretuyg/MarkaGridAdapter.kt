package com.example.eticaretuyg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MarkaGridAdapter(
    private val markaList: List<Marka>): RecyclerView.Adapter<MarkaGridAdapter.MarkaViewHolder>() {
    inner class MarkaViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                val markaResim: ImageView = itemView.findViewById(R.id.markaResim)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_marka_grid,parent,false)
        return MarkaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarkaViewHolder, position: Int) {
        val marka = markaList[position]
        holder.markaResim.setImageResource(marka.markaResim)
    }

    override fun getItemCount(): Int = markaList.size


}