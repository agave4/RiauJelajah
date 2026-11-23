package com.example.riaujelajah.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.riaujelajah.databinding.ItemWisataBinding

class WisataAdapter(private var listWisata: List<WisataModel>) :
    RecyclerView.Adapter<WisataAdapter.WisataViewHolder>() {

    inner class WisataViewHolder(val binding: ItemWisataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val binding = ItemWisataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WisataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        val item = listWisata[position]
        holder.binding.tvWisataName.text = item.name
        holder.binding.tvWisataLocation.text = item.location
        holder.binding.tvWisataPrice.text = item.price

        Glide.with(holder.itemView.context)
            .load(item.imageResId)
            .centerCrop()
            .into(holder.binding.imgWisata)

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Memilih: ${item.name} (${item.location})",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = listWisata.size

    fun updateData(newList: List<WisataModel>) {
        listWisata = newList
        notifyDataSetChanged()
    }
}