package com.example.eticaretuyg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretuyg.UrunDeposu.tumUrunler
import com.example.eticaretuyg.databinding.FragmentUrunBinding

class UrunFragment : Fragment() {

    private var _binding: FragmentUrunBinding? = null
    private val binding get() = _binding!!

    private lateinit var urunAdapter: UrunAdapter
    private lateinit var favoriViewModel: FavoriViewModel
    private var urunList: MutableList<UrunModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[FavoriViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUrunBinding.inflate(inflater, container, false)

        // Eğer alt kategori id geldi ise filtrele
        val altKategoriId = arguments?.getInt("altKategoriId")
        urunList = if (altKategoriId != null) {
            tumUrunler.filter { it.altKategoriId == altKategoriId }.map { it.toUrunModel() }.toMutableList()
        } else {
            tumUrunler.map { it.toUrunModel() }.toMutableList()
        }


        urunAdapter = UrunAdapter(
            urunList,
            onUrunClick = { urun ->
                val bundle = Bundle().apply { putParcelable("urun", urun) }
                findNavController().navigate(R.id.action_urunFragment2_to_urunDetayFragment, bundle)

            },
            onFavoriClick = { urun ->
                favoriViewModel.favoriyeEkle(urun.toFavoriUrun())
            },
            onFavoriCikar = { urun ->
                favoriViewModel.favoridenCikar(urun.toFavoriUrun())
            }
        )

        binding.recyclerTumUrunler.layoutManager = GridLayoutManager(requireContext(), 2) // 2 sütun
        binding.recyclerTumUrunler.adapter = urunAdapter


        favoriViewModel.favoriler.observe(viewLifecycleOwner) { favoriList ->
            val favoriIds = favoriList.map { it.id }
            urunList.forEach { it.isFavori = favoriIds.contains(it.id) }
            urunAdapter.updateList(urunList)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
