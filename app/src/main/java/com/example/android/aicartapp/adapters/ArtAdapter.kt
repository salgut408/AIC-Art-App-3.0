package com.example.android.aicartapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.aicartapp.R
import com.example.android.aicartapp.databinding.ItemPrevNewBinding
import com.example.example.ArtworkObject

class ArtAdapter : RecyclerView.Adapter<ArtAdapter.ArtworkObjectViewHolder>() {
    lateinit var binding: ItemPrevNewBinding
//        inner class ArtworkObjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
inner class ArtworkObjectViewHolder(binding: ItemPrevNewBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(artworkObject: ArtworkObject){
        binding.artwork=artworkObject
        binding.executePendingBindings()
    }
}

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
     val from = LayoutInflater.from(parent.context)
         binding = ItemPrevNewBinding.inflate(from, parent, false)
        return ArtworkObjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArtworkObjectViewHolder, position: Int) {
        val artwork = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(artwork.getOtherImgUrl()).into(binding.imageView)

        holder.also {



            setOnClickListener {
                onItemClickListener?.let { it(artwork) }
            }
            it.bind(artwork)

        }
        }
    }

    private var onItemClickListener:((ArtworkObject) -> Unit)? = null

    fun setOnItemClickListener(listener: (ArtworkObject) -> Unit) {
        onItemClickListener = listener
    }


}


















