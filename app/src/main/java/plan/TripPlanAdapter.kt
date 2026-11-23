package com.example.riaujelajah.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riaujelajah.data.entity.TripPlanEntity
import com.example.riaujelajah.databinding.ItemTripPlanBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TripPlanAdapter(
    private val plans: List<TripPlanEntity>,
    private val onDelete: (TripPlanEntity) -> Unit
) : RecyclerView.Adapter<TripPlanAdapter.PlanViewHolder>() {

    class PlanViewHolder(val binding: ItemTripPlanBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = ItemTripPlanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val item = plans[position]
        holder.binding.tvDestinationName.text = item.destinationName
        holder.binding.tvCityDate.text = "${item.city} • ${item.visitDate}"
        holder.binding.tvNotes.text = item.notes

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Rencana Wisata")
                .setMessage("Yakin ingin menghapus '${item.destinationName}' dari daftar?")
                .setPositiveButton("Ya") { _, _ -> onDelete(item) }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun getItemCount(): Int = plans.size
}