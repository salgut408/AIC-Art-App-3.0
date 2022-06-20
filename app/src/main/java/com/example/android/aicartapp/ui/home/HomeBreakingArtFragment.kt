package com.example.android.aicartapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.adapters.ArtAdapter
import com.example.android.aicartapp.databinding.FragmentBreakingArtBinding
import com.example.android.aicartapp.ui.MainArtViewModel
import com.example.android.aicartapp.util.Constants.Companion.QUERY_PAGE_SIZE
import com.example.android.aicartapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_art.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeBreakingArtFragment : Fragment() {
    val viewModel: MainArtViewModel by viewModel<MainArtViewModel>()
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
//        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        artAdapter.setOnItemClickListener {

            this.findNavController().navigate(
                HomeBreakingArtFragmentDirections.actionNavigationHomeToArtworkDetailFragment(it)
            )
        }

        viewModel.breakingArt.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { artResponse ->
                        artAdapter.differ.submitList(artResponse.artworkObject.toList())
                        val totalPages = artResponse.pagination!!.total!! / QUERY_PAGE_SIZE +2
                        isLastPage = viewModel.breakingArtPage == totalPages
                        if(isLastPage) {
                            binding.rvBreakingArt.setPadding(0,0,0,0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "an error occured: $message", Toast.LENGTH_LONG).show()
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
        isLoading=true
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading=false
    }




    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeggining = firstVisibleItemPosition >=0
            val isTotalMoreThanVisible = totalItemCount>=QUERY_PAGE_SIZE

            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeggining &&
                    isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                viewModel.getBreakingArt()
                isScrolling = false
            }

        }
    }


    private fun setUpRecyclerView( ) {
        artAdapter = ArtAdapter()
        binding.rvBreakingArt.apply {
            adapter = artAdapter
            layoutManager = LinearLayoutManager(activity)

            addOnScrollListener(this@HomeBreakingArtFragment.scrollListener)
        }
    }






}






















