package com.example.riaujelajah.tutorial

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.riaujelajah.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pages = listOf(
            TutorialPageFragment(
                "Jelajahi Wisata Riau",
                "Temukan destinasi alam, sejarah istana Melayu, hingga kuliner khas dalam satu genggaman.",
                Color.parseColor("#00695C")
            ),
            TutorialPageFragment(
                "Filter Wilayah Praktis",
                "Gunakan tombol filter wilayah Pekanbaru, Kampar, atau Siak untuk merencanakan liburan akhir pekanmu.",
                Color.parseColor("#004D40")
            ),
            TutorialPageFragment(
                "Promo & Reservasi Mudah",
                "Dapatkan potongan harga khusus mahasiswa dan catat reservasi tiket wisata secara instan.",
                Color.parseColor("#2E7D32")
            )
        )

        val adapter = TutorialAdapter(this, pages)
        binding.viewPagerTutorial.adapter = adapter
        binding.dotIndicator.attachTo(binding.viewPagerTutorial)

        binding.btnTutupTutorial.setOnClickListener {
            finish()
        }
    }
}