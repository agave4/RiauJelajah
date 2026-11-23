package com.example.riaujelajah.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.riaujelajah.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var tabsAdapter: HomeTabsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "RiauJelajah"
        setupTabs()
        setupRegionChips()
    }

    private fun setupTabs() {
        tabsAdapter = HomeTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        val tabTitles = listOf("Alam", "Sejarah", "Kuliner")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun setupRegionChips() {
        binding.chipGroupWilayah.setOnCheckedStateChangeListener { group, checkedIds ->
            val checkedId = checkedIds.firstOrNull()
            val selectedRegion = if (checkedId != null) {
                group.findViewById<Chip>(checkedId)?.text?.toString() ?: "Semua"
            } else {
                "Semua"
            }
            tabsAdapter.filterAllTabs(selectedRegion)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}