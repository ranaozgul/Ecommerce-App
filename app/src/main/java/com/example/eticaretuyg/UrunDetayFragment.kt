package com.example.eticaretuyg

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.eticaretuyg.databinding.FragmentUrunDetayBinding

class UrunDetayFragment : Fragment() {

    private var _binding: FragmentUrunDetayBinding? = null
    private val binding get() = _binding!!
    private var urun: UrunModel? = null
    private lateinit var favoriViewModel: FavoriViewModel
    private lateinit var sepetViewModel: SepetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[FavoriViewModel::class.java]

        sepetViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[SepetViewModel::class.java]

        // Bundle’dan ürünü alıyoruz
        urun = when (val parcel = arguments?.getParcelable<Parcelable>("urun")) {
            is UrunModel -> parcel
            is FavoriUrun -> parcel.toUrunModel()
            else -> null
        }

        if (urun == null) {
            Toast.makeText(requireContext(), "Ürün bilgisi yok", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUrunDetayBinding.inflate(inflater, container, false)

        urun?.let { u ->
            binding.textViewUrunDetayIsim.text = u.isim
            binding.textViewUrunDetayFiyat.text = "${u.fiyat} ₺"
            Glide.with(this).load(u.resim).into(binding.imageViewUrunDetayResim)

            // Favori durumu
            binding.favoriButtonDetay.setImageResource(
                if (u.isFavori) R.drawable.ic_favorite else R.drawable.bosfavorite
            )

            // Sepete ekle
            binding.buttonSepeteEkleDetay.setOnClickListener {
                sepetViewModel.sepeteEkle(u.toSepetUrun())
                Toast.makeText(requireContext(), "Sepete eklendi", Toast.LENGTH_SHORT).show()
            }

            // Favori butonu
            binding.favoriButtonDetay.setOnClickListener {
                u.isFavori = !u.isFavori
                binding.favoriButtonDetay.setImageResource(
                    if (u.isFavori) R.drawable.ic_favorite else R.drawable.bosfavorite
                )
                if (u.isFavori) favoriViewModel.favoriyeEkle(u.toFavoriUrun())
                else favoriViewModel.favoridenCikar(u.toFavoriUrun())
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
