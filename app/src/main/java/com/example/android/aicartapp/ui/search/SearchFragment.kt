package com.example.android.aicartapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.adapters.ArtAdapter
import com.example.android.aicartapp.databinding.FragmentBreakingArtBinding
import com.example.android.aicartapp.databinding.FragmentSearchBinding
import com.example.android.aicartapp.ui.MainArtViewModel
import com.example.android.aicartapp.util.Constants.Companion.SEARCH_ART_TIME_DELAY
import com.example.android.aicartapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModel: MainArtViewModel
    lateinit var artAdapter: ArtAdapter
    val TAG = "SearchArtFragment"
    lateinit var binding: FragmentSearchBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        // delay on search request & TextChangedListener on search
        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
               delay(SEARCH_ART_TIME_DELAY)
                editable?.let{
                    if(editable.toString().isNotEmpty()) {
                        viewModel.searchArt(editable.toString())
                    }
                }
            }
        }


        viewModel.searchArt.observe(viewLifecycleOwner, Observer { response ->
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
        rvSearchArt.apply {
            adapter = artAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }



}