package com.salgut.android.aicartapp.ui.artworkDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.salgut.android.aicartapp.ui.MainArtViewModel
import com.google.android.material.snackbar.Snackbar
import com.salgut.android.aicartapp.databinding.FragmentArtworkDetailBinding

import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtworkDetailFragment : Fragment() {
    val viewModel: MainArtViewModel by viewModel<MainArtViewModel>()
    lateinit var binding: FragmentArtworkDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentArtworkDetailBinding.inflate(inflater)
        val artwork = ArtworkDetailFragmentArgs.fromBundle(requireArguments()).selectedArtwork
        binding.artwork = artwork
        binding.artwork
        binding.fabMap.setOnClickListener {
            this.findNavController()
                .navigate(ArtworkDetailFragmentDirections.actionArtworkDetailFragmentToMapsFragment(
                    artwork))
        }

        binding.floatingActionButton.setOnClickListener {
            viewModel.saveArtwork(artwork)
            Snackbar.make(binding.root, "Saved Artwork", Snackbar.LENGTH_LONG).show()

        }
        return binding.root

    }

}



























