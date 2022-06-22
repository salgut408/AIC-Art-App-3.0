package com.example.android.aicartapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.aicartapp.R
import com.example.example.ArtworkObject
import kotlinx.android.synthetic.main.item_artwork_preview.view.*
import kotlinx.android.synthetic.main.item_prev_new.view.*

class ArtAdapter : RecyclerView.Adapter<ArtAdapter.ArtworkObjectViewHolder>() {
//        inner class ArtworkObjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
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
            Glide.with(this).load(artwork.getOtherImgUrl()).into(imageView)
            textView2.text=artwork.artistDisplay
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


















