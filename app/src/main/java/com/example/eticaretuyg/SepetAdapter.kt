package com.example.eticaretuyg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretuyg.databinding.ItemSepetBinding

class SepetAdapter(
    private var sepetList: MutableList<SepetUrun>,
    private val onArttirClick: (SepetUrun) -> Unit,
    private val onAzaltClick: (SepetUrun) -> Unit,
    private val onSilClick: (SepetUrun) -> Unit
) : RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {

    inner class SepetViewHolder(val binding: ItemSepetBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val binding = ItemSepetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SepetViewHolder(binding)
    }

    override fun getItemCount(): Int = sepetList.size

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        val urun = sepetList[position]
        holder.binding.imageUrun.setImageResource(urun.resim)
        holder.binding.textUrunIsim.text = urun.isim
        holder.binding.textUrunFiyat.text = "₺${urun.fiyat}"
        holder.binding.textAdet.text = urun.adet.toString()
        holder.binding.btnArttir.setOnClickListener {
            onArttirClick(urun)
        }
        holder.binding.btnAzalt.setOnClickListener {
            onAzaltClick(urun)
        }
        holder.binding.btnSepettenCikar.setOnClickListener {
            onSilClick(urun)
        }
    }

    fun updateList(newList: List<SepetUrun>) {
        sepetList.clear()
        sepetList.addAll(newList)
        notifyDataSetChanged()
    }
}
