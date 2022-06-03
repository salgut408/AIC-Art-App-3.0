package com.example.android.aicartapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.databinding.FragmentSearchBinding
import com.example.android.aicartapp.ui.MainArtViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModel: MainArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.inflate(layoutInflater)
        viewModel = (activity as MainActivity).viewModel

    }



}