package com.example.android.aicartapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.databinding.FragmentFavoritesBinding
import com.example.android.aicartapp.databinding.FragmentSearchBinding
import com.example.android.aicartapp.ui.MainArtViewModel

class FavoritesFragment : Fragment() {
    lateinit var viewModel: MainArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesBinding.inflate(layoutInflater)
        viewModel = (activity as MainActivity).viewModel

    }


}