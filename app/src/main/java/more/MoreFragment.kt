package com.example.riaujelajah.more

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.riaujelajah.LoginActivity
import com.example.riaujelajah.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Layanan & Profil"

        binding.btnPesanTiket.setOnClickListener {
            val nama = binding.etNamaPemesan.text.toString().trim()
            val tujuan = binding.etTujuan.text.toString().trim()

            if (nama.isEmpty() || tujuan.isEmpty()) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Data Belum Lengkap")
                    .setMessage("Silakan isi nama pemesan dan destinasi tujuan.")
                    .setPositiveButton("OK", null)
                    .show()
            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle("Reservasi Berhasil")
                    .setMessage("Tiket atas nama $nama ke $tujuan berhasil dicatat!")
                    .setPositiveButton("Selesai") { d, _ ->
                        binding.etNamaPemesan.text.clear()
                        binding.etTujuan.text.clear()
                        d.dismiss()
                    }
                    .show()
            }
        }

        binding.btnPortalPariwisata.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari RiauJelajah?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}