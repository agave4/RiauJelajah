package com.example.riaujelajah.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.riaujelajah.R
import com.example.riaujelajah.databinding.FragmentWisataListBinding

class WisataListFragment : Fragment() {

    private var _binding: FragmentWisataListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: WisataAdapter
    private var category: String = "Alam"
    private var currentRegion: String = "Semua"

    companion object {
        private const val ARG_CATEGORY = "category"

        fun newInstance(category: String): WisataListFragment {
            val fragment = WisataListFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getString(ARG_CATEGORY) ?: "Alam"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWisataListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WisataAdapter(emptyList())
        binding.rvWisata.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWisata.adapter = adapter
        filterData(currentRegion)
    }

    fun filterData(region: String) {
        currentRegion = region
        if (_binding == null) return

        val filtered = getDummyData().filter { item ->
            val matchCategory = item.category.equals(category, ignoreCase = true)
            val matchRegion = region.equals("Semua", ignoreCase = true) ||
                    item.location.contains(region, ignoreCase = true)
            matchCategory && matchRegion
        }
        adapter.updateData(filtered)
    }

    private fun getDummyData(): List<WisataModel> = listOf(
        // Kategori Alam
        WisataModel("Ombak Bono Sungai Kampar", "Pelalawan", "Rp 20.000", "Alam", R.drawable.img_ombak_bono),
        WisataModel("Air Terjun Batu Dinding", "Kampar", "Rp 15.000", "Alam", R.drawable.img_batu_dinding),
        WisataModel("Ulu Kasok", "Kampar", "Rp 15.000", "Alam", R.drawable.img_ulu_kasok),
        WisataModel("Danau Buatan Lembah Sari", "Pekanbaru", "Rp 10.000", "Alam", R.drawable.img_danau_buatan),
        WisataModel("Taman Nasional Tesso Nilo", "Pelalawan", "Rp 25.000", "Alam", R.drawable.img_tesso_nilo),
        WisataModel("Pantai Rupat Utara", "Bengkalis", "Rp 10.000", "Alam", R.drawable.img_pantai_rupat),

        // Kategori Sejarah
        WisataModel("Istana Siak Sri Indrapura", "Siak", "Rp 15.000", "Sejarah", R.drawable.img_istana_siak),
        WisataModel("Candi Muara Takus", "Kampar", "Rp 15.000", "Sejarah", R.drawable.img_candi_muara_takus),
        WisataModel("Masjid Raya An-Nur", "Pekanbaru", "Gratis", "Sejarah", R.drawable.img_masjid_annur),
        WisataModel("Museum Sang Nila Utama", "Pekanbaru", "Rp 5.000", "Sejarah", R.drawable.img_museum_sang_nila),
        WisataModel("Perpustakaan Soeman HS", "Pekanbaru", "Gratis", "Sejarah", R.drawable.img_pustaka_soeman),
        WisataModel("Benteng Tujuh Lapis", "Rokan Hulu", "Rp 10.000", "Sejarah", R.drawable.img_benteng_tujuh_lapis),

        // Kategori Kuliner
        WisataModel("Bolu Kemojo", "Pekanbaru", "Rp 25.000", "Kuliner", R.drawable.img_bolu_kemojo),
        WisataModel("Gulai Ikan Patin", "Kampar", "Rp 35.000", "Kuliner", R.drawable.img_gulai_patin),
        WisataModel("Mie Sagu", "Meranti", "Rp 18.000", "Kuliner", R.drawable.img_mie_sagu),
        WisataModel("Lempuk Durian", "Bengkalis", "Rp 45.000", "Kuliner", R.drawable.img_lempuk_durian),
        WisataModel("Lopek Bugi", "Kampar", "Rp 10.000", "Kuliner", R.drawable.img_lopek_bugi),
        WisataModel("Es Laksamana Mengamuk", "Pekanbaru", "Rp 15.000", "Kuliner", R.drawable.img_laksamana_mengamuk)
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}