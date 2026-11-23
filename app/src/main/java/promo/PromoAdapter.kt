package com.example.riaujelajah.promo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.riaujelajah.databinding.ItemPromoBinding

class PromoAdapter(
    private val context: Context,
    private val listPromo: List<PromoModel>
) : BaseAdapter() {

    override fun getCount(): Int = listPromo.size

    override fun getItem(position: Int): Any = listPromo[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemPromoBinding = if (convertView == null) {
            ItemPromoBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemPromoBinding.bind(convertView)
        }

        val item = listPromo[position]
        binding.tvPromoTitle.text = item.title
        binding.tvPromoDesc.text = item.description

        Glide.with(context)
            .load(item.imageResId)
            .centerCrop()
            .into(binding.imgPromo)

        return binding.root
    }
}