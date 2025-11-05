package com.example.eticaretuyg

import UrunViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretuyg.R.drawable.ic_launcher_background
import com.example.eticaretuyg.databinding.FragmentAnaBinding



class anaFragment : Fragment(R.layout.fragment_ana) {
    private var _binding: FragmentAnaBinding? = null
    private val binding get() = _binding!!
    private lateinit var urunViewModel: UrunViewModel
    private lateinit var urunAdapter: UrunAdapter
    private lateinit var favoriViewModel: FavoriViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urunDao = UygDatabase.getDatabase(requireContext()).urunDao()
        val factory = UrunViewModelFactory(urunDao)
        val sliderImages = listOf(R.drawable.indirimresim,R.drawable.maviresim,R.drawable.tommyresim,R.drawable.indirimresim)
        val sliderAdapter = SliderAdapter(sliderImages)
        binding.viewPagerSlider.adapter = sliderAdapter
        urunViewModel = ViewModelProvider(this, factory)[UrunViewModel::class.java]
        val dao = UygDatabase.getDatabase(requireContext()).favoriUrunDao()
        favoriViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[FavoriViewModel::class.java]


        val enCokSatanlar = listOf(
            EnCokSatan("T-shirt", "₺149.99", R.drawable.kadinvangoghtshirt),
            EnCokSatan("Ayakkabı", "₺1249.99", R.drawable.kadinbotayakkabi),
            EnCokSatan("Gömlek", "₺399.20", R.drawable.kadinsiyahgomlek)
        )
        val enCokSatanAdapter = EnCokSatanAdapter(enCokSatanlar)
        binding.recyclerViewEnCokSatan.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.recyclerViewEnCokSatan.adapter = enCokSatanAdapter

        val tumMarkalar = listOf(
            Marka("", R.drawable.nike),
            Marka("", R.drawable.adidas),
            Marka("", R.drawable.gucci),
            Marka("", R.drawable.chanel),
            Marka("", R.drawable.louisvitton),
            Marka("", R.drawable.dg),
            Marka("", R.drawable.lacoste),
            Marka("", R.drawable.versace),
            Marka("", R.drawable.levis),
            Marka("", R.drawable.calvinklein),
            Marka("", R.drawable.burberry),
            Marka("", R.drawable.prada)
        )
        val markaListesi = tumMarkalar // Tüm markaların listesi
        val sayfaBoyutu = 6 // Her sayfada 6 marka gösterilecek (2 sütun x 3 satır)

        val sayfaListesi = markaListesi.chunked(sayfaBoyutu) // Markaları sayfalara böl

        val markaPagerAdapter = MarkaPagerAdapter(requireContext(), sayfaListesi)
        binding.viewPagerMarkalar.adapter = markaPagerAdapter

        val tumUrunler = UrunDeposu.tumUrunler
        val rastgeleUrunler = tumUrunler.shuffled().take(9)
        val urunModelListesi = rastgeleUrunler.map { it.toUrunModel() }.toMutableList()



        urunAdapter = UrunAdapter(
            urunModelListesi,
            onUrunClick = { urun ->
                val bundle = Bundle().apply {  putParcelable("urun",urun)}
                findNavController().navigate(R.id.action_anaFragment_to_urunDetayFragment,bundle)
            },
            onFavoriClick = { urun ->
                favoriViewModel.favoriyeEkle(urun.toFavoriUrun())
            },
            onFavoriCikar = { urun ->
                favoriViewModel.favoridenCikar(urun.toFavoriUrun())
            })




        binding.recyclerViewRastgeleUrunler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewRastgeleUrunler.adapter = urunAdapter


        favoriViewModel.favoriler.observe(viewLifecycleOwner) { favoriList ->
            val favoriIds = favoriList.map { it.id }
            urunModelListesi.forEach { it.isFavori = favoriIds.contains(it.id) }
            urunAdapter.updateList(urunModelListesi)
        }


    }
    private fun bolMarkalariSayfalara(
        markaListesi: List<Marka>,
        sayfaBoyutu: Int
    ): List<List<Marka>> {
        val sayfalar = mutableListOf<List<Marka>>()
        var i = 0
        while (i < markaListesi.size) {
            val altListe = markaListesi.subList(i, minOf(i + sayfaBoyutu, markaListesi.size))
            sayfalar.add(altListe)
            i += sayfaBoyutu
        }
        return sayfalar
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

