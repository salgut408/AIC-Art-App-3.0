package com.salgut.android.aicartapp.adapters

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.salgut.android.aicartapp.R
import com.salgut.android.aicartapp.databinding.ItemPrevNewBinding
import com.salgut.android.aicartapp.models.ArtworkObject
import kotlinx.android.synthetic.main.fragment_artwork_detail.view.*
import kotlinx.android.synthetic.main.item_prev_new.view.*
import kotlinx.android.synthetic.main.item_prev_new.view.artistNameDisplay

class ArtAdapter : RecyclerView.Adapter<ArtAdapter.ArtworkObjectViewHolder>() {
inner class ArtworkObjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

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
        return ArtworkObjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_prev_new,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArtworkObjectViewHolder, position: Int) {
        val artwork = differ.currentList[position]
        holder.itemView.apply {

            val url = artwork.getOtherImgUrl()

            Glide.with(this)
                .asBitmap()
                .load(url)
                .into(imageView)
            artistTitle.text=artwork.artistDisplay
            artistNameDisplay.text=artwork.title

            setOnClickListener {
                onItemClickListener?.let { it(artwork) }
            }
        }
    }

    private var onItemClickListener:((ArtworkObject) -> Unit)? = null

    fun setOnItemClickListener(listener: (ArtworkObject) -> Unit) {
        onItemClickListener = listener
    }
}


















