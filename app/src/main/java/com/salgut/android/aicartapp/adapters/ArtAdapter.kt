package com.salgut.android.aicartapp.adapters

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salgut.android.aicartapp.databinding.ItemPrevNewBinding
import com.salgut.android.aicartapp.models.ArtworkObject


class ArtAdapter : RecyclerView.Adapter<ArtAdapter.ArtworkObjectViewHolder>() {
    inner class ArtworkObjectViewHolder(val binding: ItemPrevNewBinding) :
        RecyclerView.ViewHolder(binding.root)

    var dominantColor: Int = 0

    private val differCallback = object : DiffUtil.ItemCallback<ArtworkObject>() {
        override fun areItemsTheSame(oldItem: ArtworkObject, newItem: ArtworkObject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArtworkObject, newItem: ArtworkObject): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkObjectViewHolder {
        return ArtworkObjectViewHolder(ItemPrevNewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArtworkObjectViewHolder, position: Int) {
        val artwork = differ.currentList[position]
        holder.binding.apply {

            artistTitle.text = artwork.artistDisplay
            artistNameDisplay.text = artwork.title

            var url = artwork.getOtherImgUrl()

            Glide.with(imageView.context)


                .load(url)


                .into(imageView)



            cardItem.setOnClickListener {
                onItemClickListener?.let { it(artwork) }
            }


        }
    }

    private var onItemClickListener: ((ArtworkObject) -> Unit)? = null

    fun setOnItemClickListener(listener: (ArtworkObject) -> Unit) {
        onItemClickListener = listener
    }


    fun getDomColor(drawable: Drawable, binding: TextView) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate { palette ->

            palette?.dominantSwatch?.rgb?.toInt()
                ?.let { binding.setTextColor(it) }
        }
    }


}


















