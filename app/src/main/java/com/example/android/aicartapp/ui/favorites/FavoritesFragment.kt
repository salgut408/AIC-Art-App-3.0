package com.example.android.aicartapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.adapters.ArtAdapter
import com.example.android.aicartapp.databinding.FragmentFavoritesBinding
import com.example.android.aicartapp.databinding.FragmentSearchBinding
import com.example.android.aicartapp.ui.MainArtViewModel
import com.google.android.material.snackbar.Snackbar
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setUpRecyclerView()

        artAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("selectedArtwork", it)
            }
            this.findNavController().navigate(FavoritesFragmentDirections.actionNavigationFavoritesToArtworkDetailFragment(it)
            )
        }


        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val artwork = artAdapter.differ.currentList[position]
                viewModel.deleteArt(artwork)
                Snackbar.make(binding.root, "deleted Artwork", Snackbar.LENGTH_LONG ).apply {
                    setAction("undo") {
                        viewModel.saveArtwork(artwork)
                }
                    show()
                }
            }
        }



        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedArt)
        }

        viewModel.getSavedArt().observe(viewLifecycleOwner, Observer { artworks ->
            artAdapter.differ.submitList(artworks)

        })

    }

    private fun setUpRecyclerView( ) {
        artAdapter = ArtAdapter()
        binding.rvSavedArt.apply {
            adapter = artAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}























