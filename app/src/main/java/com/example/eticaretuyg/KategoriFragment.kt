package com.example.eticaretuyg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretuyg.databinding.FragmentKategoriBinding

class KategoriFragment : Fragment(R.layout.fragment_kategori) {

    private var _binding: FragmentKategoriBinding? = null
    private val binding get() = _binding!!

    private lateinit var kategoriAdapter: KategoriAdapter
    private val kategoriler = mutableListOf<Kategori>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (kategoriler.isEmpty()){
        kategoriler.addAll(ornekKategorileriGetir())}



        kategoriAdapter = KategoriAdapter(kategoriler) { altKategori ->
            // Alt kategoriye tıklanınca UrunFragment’e geçiş
            val bundle = Bundle()
            bundle.putInt("altKategoriId", altKategori.id)
            findNavController().navigate(R.id.action_kategoriFragment_to_urunFragment2, bundle)
        }

        binding.recyclerViewKategori.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewKategori.adapter = kategoriAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun ornekKategorileriGetir(): List<Kategori> {
        return listOf(
            Kategori(1, "Erkek", listOf(
                AltKategori(101,"Tişört",1),
                AltKategori(102,"Pantalon",1),
                AltKategori(103,"Ayakkabı",1),
                AltKategori(104,"Ceket",1),
                AltKategori(105,"Gömlek",1),
                AltKategori(106,"Kazak",1)
            )),
            Kategori(2, "Kadın", listOf(
                AltKategori(201,"Tişört",2),
                AltKategori(202,"Gömlek",2),
                AltKategori(203,"Elbise",2),
                AltKategori(204,"Ceket",2),
                AltKategori(205,"Çanta",2),
                AltKategori(206,"Ayakkabı",2),
                AltKategori(207,"Etek",2)
            )),
            Kategori(3, "Çocuk", listOf(
                AltKategori(301,"Tişört",3),
                AltKategori(302,"Pantalon",3),
                AltKategori(303,"Tulum",3),
                AltKategori(304,"Oyuncak",3),
                AltKategori(305,"Bebek İhtiyaçları",3)
            ))
        )
    }
}


