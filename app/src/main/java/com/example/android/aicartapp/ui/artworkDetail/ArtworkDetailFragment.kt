package com.example.android.aicartapp.ui.artworkDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.aicartapp.MainActivity
import com.example.android.aicartapp.R
import com.example.android.aicartapp.databinding.FragmentArtworkDetailBinding
import com.example.android.aicartapp.ui.MainArtViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtworkDetailFragment : Fragment() {
    val viewModel: MainArtViewModel by viewModel<MainArtViewModel>()
    lateinit var binding: FragmentArtworkDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtworkDetailBinding.inflate(inflater)
        val artwork = ArtworkDetailFragmentArgs.fromBundle(requireArguments()).selectedArtwork
        binding.artwork = artwork


//  in case using a webview instead of image view...
//        binding.webView.apply {
//            webViewClient = WebViewClient()
//            loadUrl(artwork.getOtherImgUrl())
//        }

    binding.fabMap.setOnClickListener {
        this.findNavController().navigate(ArtworkDetailFragmentDirections.actionArtworkDetailFragmentToMapsFragment(artwork))
    }


        binding.floatingActionButton.setOnClickListener {
            viewModel.saveArtwork(artwork)
            Snackbar.make(binding.root, "Saved Artwork", Snackbar.LENGTH_LONG ).show()

        }
        return binding.root


    }






}



























