package com.example.eticaretuyg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretuyg.databinding.FragmentFavorilerBinding

class FavorilerFragment : Fragment() {

    private var _binding: FragmentFavorilerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriAdapter
    private lateinit var favoriViewModel: FavoriViewModel
    private lateinit var sepetViewModel: SepetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavorilerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel'leri oluştur
        favoriViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[FavoriViewModel::class.java]

        sepetViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SepetViewModel::class.java]

        // Adapter
        adapter = FavoriAdapter(
            mutableListOf(),
            onUrunClick = { urun ->
                val bundle = Bundle()
                bundle.putParcelable("urun", urun.toUrunModel())
                findNavController().navigate(R.id.action_favorilerFragment_to_urunDetayFragment, bundle)
            },
            onFavoridenCikar = { urun ->
                favoriViewModel.favoridenCikar(urun)
            },
            onSepeteEkle = { urun ->
                sepetViewModel.sepeteEkle(urun)
                Toast.makeText(requireContext(), "Sepete eklendi", Toast.LENGTH_SHORT).show()
                android.util.Log.d("Favoriler", "Sepete eklendi: ${urun.id} - ${urun.isim}")
            }
        )

        binding.recyclerViewFavoriler.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavoriler.adapter = adapter


        favoriViewModel.favoriler.observe(viewLifecycleOwner) { favoriList ->
            adapter.updateList(favoriList )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

