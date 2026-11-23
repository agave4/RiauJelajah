package com.example.riaujelajah.promo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.riaujelajah.R
import com.example.riaujelajah.databinding.FragmentPromoBinding

class PromoFragment : Fragment() {

    private var _binding: FragmentPromoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "Promo Wisata Riau"

        val promoList = listOf(
            PromoModel(
                title = "Promo Jelajah Siak & Alam Riau (Hemat 50%)",
                description = "Diskon s/d 50% tiket masuk Istana Siak, Ombak Bono, dan Tesso Nilo. Kode: RIAUALAM50",
                imageResId = R.drawable.img_promo_alam
            ),
            PromoModel(
                title = "Promo Jelajah Budaya & Sejarah (Hemat 50%)",
                description = "Paket wisata edukasi Candi Muara Takus, Masjid An-Nur, dan Museum. Kode: BUDAYARIAU50",
                imageResId = R.drawable.img_promo_budaya
            ),
            PromoModel(
                title = "Pesta Kuliner Khas Riau (Diskon 30%)",
                description = "Hemat 30% paket keluarga Gulai Ikan Patin, Mie Sagu, dan Bolu Kemojo. Kode: KULINERRIAU30",
                imageResId = R.drawable.img_promo_kuliner
            )
        )

        val adapter = PromoAdapter(requireContext(), promoList)
        binding.listPromo.adapter = adapter

        binding.listPromo.setOnItemClickListener { _, _, position, _ ->
            val selected = promoList[position]
            Toast.makeText(requireContext(), "Promo dipilih: ${selected.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}