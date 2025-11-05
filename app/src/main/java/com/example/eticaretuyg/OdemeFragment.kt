package com.example.eticaretuyg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.eticaretuyg.databinding.FragmentOdemeBinding


class OdemeFragment : Fragment() {

    private var _binding: FragmentOdemeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sepetViewModel: SepetViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOdemeBinding.inflate(inflater, container, false)
        sepetViewModel = ViewModelProvider(requireActivity())[SepetViewModel::class.java]

        binding.buttonOdemeYap.setOnClickListener {
            val kartNo = binding.editTextKartNo.text.toString()
            val isim = binding.editTextKartIsim.text.toString()
            val sonKullanma = binding.editTextSonKullanma.text.toString()
            val cvc = binding.editTextCVV.text.toString()

            if (kartNo.isNotEmpty() && isim.isNotEmpty() && sonKullanma.isNotEmpty() && cvc.isNotEmpty()) {
                Toast.makeText(requireContext(), "Ödeme başarılı!", Toast.LENGTH_SHORT).show()
                sepetViewModel.sepetiBosalt()
                parentFragmentManager.popBackStack() // Sepete geri dön
            } else {
                Toast.makeText(requireContext(), "Lütfen tüm bilgileri doldurun", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
