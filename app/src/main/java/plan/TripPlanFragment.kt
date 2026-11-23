package com.example.riaujelajah.plan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riaujelajah.data.AppDatabase
import com.example.riaujelajah.data.entity.TripPlanEntity
import com.example.riaujelajah.databinding.FragmentTripPlanBinding
import kotlinx.coroutines.launch

class TripPlanFragment : Fragment() {

    private var _binding: FragmentTripPlanBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: TripPlanAdapter
    private val plans = mutableListOf<TripPlanEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTripPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        adapter = TripPlanAdapter(plans) { plan -> deletePlan(plan) }

        binding.rvTripPlans.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTripPlans.adapter = adapter

        binding.fabAddPlan.setOnClickListener {
            startActivity(Intent(requireContext(), TripPlanFormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        if (_binding != null) fetchPlans()
    }

    private fun fetchPlans() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = db.tripPlanDao().getAll()
            plans.clear()
            plans.addAll(result)
            adapter.notifyDataSetChanged()
        }
    }

    private fun deletePlan(plan: TripPlanEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.tripPlanDao().delete(plan)
            fetchPlans()
        }
    }

    override fun onDestroyView() {
        binding.rvTripPlans.adapter = null
        _binding = null
        super.onDestroyView()
    }
}