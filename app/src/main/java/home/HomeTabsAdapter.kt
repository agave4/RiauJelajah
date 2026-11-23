package com.example.riaujelajah.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(
        WisataListFragment.newInstance("Alam"),
        WisataListFragment.newInstance("Sejarah"),
        WisataListFragment.newInstance("Kuliner")
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun filterAllTabs(region: String) {
        fragments.forEach { it.filterData(region) }
    }
}