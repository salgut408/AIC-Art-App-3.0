package com.example.android.aicartapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.adapters.ArtAdapter
import com.example.android.aicartapp.databinding.FragmentFavoritesBinding
import com.example.android.aicartapp.databinding.FragmentSearchBinding
import com.example.android.aicartapp.ui.MainArtViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class FavoritesFragment : Fragment() {
    lateinit var viewModel: MainArtViewModel
    lateinit var binding: FragmentFavoritesBinding
    lateinit var artAdapter: ArtAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setUpRecyclerView()

        artAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("artwork", it)
            }
            this.findNavController().navigate(
                R.id.action_navigation_search_to_artworkDetailFragment,
                bundle
            )
        }



    }

    private fun setUpRecyclerView( ) {
        artAdapter = ArtAdapter()
        rvSearchArt.apply {
            adapter = artAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}























