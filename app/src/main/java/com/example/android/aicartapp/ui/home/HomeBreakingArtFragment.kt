package com.example.android.aicartapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.adapters.ArtAdapter
import com.example.android.aicartapp.databinding.FragmentBreakingArtBinding
import com.example.android.aicartapp.ui.MainArtViewModel
import com.example.android.aicartapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_art.*

class HomeBreakingArtFragment : Fragment() {
    lateinit var viewModel: MainArtViewModel
    lateinit var artAdapter: ArtAdapter
    lateinit var binding: FragmentBreakingArtBinding
    val TAG = "BreakingArtHomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreakingArtBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        //binding = FragmentBreakingArtBinding.inflate(layoutInflater)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        artAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selectedArtwork", it)
            }
            this.findNavController().navigate(
                R.id.action_navigation_home_to_artworkDetailFragment,
                bundle
            )
        }

        viewModel.breakingArt.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { artResponse ->
                        artAdapter.differ.submitList(artResponse.artworkObject)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error orrcured $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }


        })
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun setUpRecyclerView( ) {
        artAdapter = ArtAdapter()
        rvBreakingArt.apply {
            adapter = artAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }






}






















