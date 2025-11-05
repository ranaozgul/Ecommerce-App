package com.example.eticaretuyg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eticaretuyg.databinding.FragmentProfilBinding


class ProfilFragment : Fragment() {

    private lateinit var binding: FragmentProfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(inflater, container, false)


        binding.textViewIsim.text = "Rana Özgül"
        binding.textViewMail.text = "ranaozgul@gmail.com"
        binding.textViewTelefon.text = "+90 555 555 55 55"
        binding.textViewAdres.text = "xxxxxx,xxxxxxxx"

        return binding.root
    }
}
