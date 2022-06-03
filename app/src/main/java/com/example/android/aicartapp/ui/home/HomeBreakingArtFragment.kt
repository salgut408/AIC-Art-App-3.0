package com.example.android.aicartapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.adapters.ArtAdapter
import com.example.android.aicartapp.databinding.FragmentBreakingArtBinding
import com.example.android.aicartapp.ui.MainArtViewModel

class HomeBreakingArtFragment : Fragment() {
    lateinit var viewModel: MainArtViewModel
    lateinit var artAdapter: ArtAdapter
    lateinit var binding: FragmentBreakingArtBinding
    val TAG = "BreakingArtHomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBreakingArtBinding.inflate(layoutInflater)
        viewModel = (activity as MainActivity).viewModel
    }






}






















