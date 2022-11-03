package com.salgut.android.aicartapp.ui.artworkDetail

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import com.salgut.android.aicartapp.databinding.FragmentArtworkDetailBinding
import com.salgut.android.aicartapp.models.ArtworkObject
import com.salgut.android.aicartapp.ui.MainArtViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class ArtworkDetailFragment : Fragment() {
    val viewModel: MainArtViewModel by viewModel<MainArtViewModel>()
    lateinit var binding: FragmentArtworkDetailBinding
    lateinit var artworkThing: ArtworkObject


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentArtworkDetailBinding.inflate(inflater)
        val artwork = ArtworkDetailFragmentArgs.fromBundle(requireArguments()).selectedArtwork
        binding.artwork = artwork

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = ArtworkDetailFragmentArgs.fromBundle(requireArguments()).selectedArtwork
        artworkThing = args

        args.let{
            try {
                Glide.with(requireActivity())
                    .load(it.getOtherImgUrl())
                    .fitCenter()
                    .listener(object: RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("TAG","ERROR loading Image",e)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            resource.let{
                                Palette.from(resource!!.toBitmap()).generate(fun(palette: Palette?){
                                    val intColorVibrant = palette?.vibrantSwatch?.rgb ?: Color.BLACK
                                    val intColorDarkVibrant = palette?.darkVibrantSwatch?.rgb ?: Color.LTGRAY
                                    val titleColor = palette?.dominantSwatch?.titleTextColor ?: Color.BLACK
                                    val textColor = palette?.dominantSwatch?.bodyTextColor ?: Color.BLACK

                                    binding.artTitle.setTextColor(titleColor)

                                    binding.floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(intColorVibrant))
                                    binding.fabMap.setBackgroundTintList(ColorStateList.valueOf(intColorVibrant))

                                    binding.artistNameDisplay.setTextColor(textColor)
                                    binding.scrollView.setBackgroundColor(intColorDarkVibrant)
                                    binding.classificationTitle.setTextColor(textColor)
                                    binding.colorfullScoreNumber.setTextColor(textColor)
                                    binding.credLine.setTextColor(textColor)
                                    binding.colorfulnessScore.setTextColor(textColor)
                                    binding.placeOfOrigin.setTextColor(textColor)
                                    binding.styleTitle.setTextColor(textColor)

                                })
                            }
                            return false
                        }
                    })
                    .transition(withCrossFade())
                    .into(binding.deatilImageView)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }




}



























