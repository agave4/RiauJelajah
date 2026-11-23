package com.example.riaujelajah

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.riaujelajah.databinding.ActivityBaseBinding
import com.example.riaujelajah.home.HomeFragment
import com.example.riaujelajah.more.MoreFragment
import com.example.riaujelajah.plan.TripPlanFragment
import com.example.riaujelajah.promo.PromoFragment

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Badge angka pada tab Promo (Pertemuan 10)
        val promoBadge = binding.bottomNavView.getOrCreateBadge(R.id.nav_promo)
        promoBadge.isVisible = true
        promoBadge.number = 4

        replaceFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_promo -> {
                    replaceFragment(PromoFragment())
                    true
                }
                R.id.nav_plan -> {
                    replaceFragment(TripPlanFragment())
                    true
                }
                R.id.nav_more -> {
                    replaceFragment(MoreFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}