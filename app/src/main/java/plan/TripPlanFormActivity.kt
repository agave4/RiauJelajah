package com.example.riaujelajah.plan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.riaujelajah.data.AppDatabase
import com.example.riaujelajah.data.entity.TripPlanEntity
import com.example.riaujelajah.databinding.ActivityTripPlanFormBinding
import kotlinx.coroutines.launch

class TripPlanFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTripPlanFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTripPlanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnSavePlan.setOnClickListener {
            val destination = binding.etDestinationName.text.toString().trim()
            val city = binding.etCity.text.toString().trim()
            val date = binding.etVisitDate.text.toString().trim()
            val notes = binding.etNotes.text.toString().trim()

            if (destination.isBlank() || city.isBlank() || date.isBlank() || notes.isBlank()) {
                Toast.makeText(this, "Isi semua kolom rencana!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.btnSavePlan.isEnabled = false
            lifecycleScope.launch {
                try {
                    db.tripPlanDao().insert(
                        TripPlanEntity(
                            destinationName = destination,
                            city = city,
                            visitDate = date,
                            notes = notes
                        )
                    )
                    Toast.makeText(
                        this@TripPlanFormActivity,
                        "Rencana wisata tersimpan!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } catch (e: Exception) {
                    binding.btnSavePlan.isEnabled = true
                    Toast.makeText(
                        this@TripPlanFormActivity,
                        "Gagal menyimpan data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}