package com.example.riaujelajah.tutorial

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.riaujelajah.databinding.FragmentTutorialPageBinding

class TutorialPageFragment(
    private val title: String = "",
    private val desc: String = "",
    private val bgColor: Int = Color.parseColor("#00695C")
) : Fragment() {

    private var _binding: FragmentTutorialPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorialPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rootLayout.setBackgroundColor(bgColor)
        binding.tvTutorialTitle.text = title
        binding.tvTutorialDesc.text = desc
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}