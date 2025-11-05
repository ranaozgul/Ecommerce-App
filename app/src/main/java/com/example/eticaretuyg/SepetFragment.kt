package com.example.eticaretuyg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretuyg.databinding.FragmentSepetBinding

class SepetFragment : Fragment() {

    private var _binding: FragmentSepetBinding? = null
    private val binding get() = _binding!!

    private lateinit var sepetViewModel: SepetViewModel
    private lateinit var sepetAdapter: SepetAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSepetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel
        sepetViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SepetViewModel::class.java]

        // Adapter
        sepetAdapter = SepetAdapter(
            mutableListOf(),
            onArttirClick = { sepetUrun ->
                sepetViewModel.adetArttir(sepetUrun.id)
            },
            onAzaltClick = { sepetUrun ->
                if (sepetUrun.adet > 1) {
                    sepetViewModel.adetAzalt(sepetUrun.id)
                } else {
                    sepetViewModel.sepettenCikar(sepetUrun)
                }
            },
            onSilClick = { sepetUrun ->
                sepetViewModel.sepettenCikar(sepetUrun)
            }
        )
        binding.buttonOdemeyeGec.setOnClickListener {
            findNavController().navigate(R.id.action_sepetFragment_to_odemeFragment)
        }


        binding.recyclerSepet.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerSepet.adapter = sepetAdapter

        sepetViewModel.sepetUrunleri.observe(viewLifecycleOwner) { uruns ->
            sepetAdapter.updateList(uruns)
        }

        sepetViewModel.toplamFiyat.observe(viewLifecycleOwner){toplam->
            binding.textViewToplamFiyat.text = "Toplam:₺${"%.2f".format(toplam)}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
