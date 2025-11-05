package com.example.eticaretuyg

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class SliderAdapter(private val images: List<Int>):
RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){
    inner class SliderViewHolder(val imageView: ImageView):
            RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): SliderViewHolder{
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return SliderViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
    }

    override fun getItemCount(): Int = images.size
}