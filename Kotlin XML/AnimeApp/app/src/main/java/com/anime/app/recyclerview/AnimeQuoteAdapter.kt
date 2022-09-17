package com.anime.app.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anime.app.R
import com.anime.app.constants.Constants
import com.anime.app.model.AnimeQuote
import com.squareup.picasso.Picasso

class AnimeQuoteAdapter(private val onClick: (AnimeQuote) -> Unit): ListAdapter<AnimeQuote,AnimeQuoteAdapter.AnimeQuoteViewModel>(AnimeQuoteDiffCallback) {


    inner class AnimeQuoteViewModel(itemView: View, val onClick: (AnimeQuote) -> Unit): RecyclerView.ViewHolder(itemView){
        private val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.anime_constraint_layout)
        private val imageView: ImageView = itemView.findViewById(R.id.anime_image_view)
        private val animeNameText : TextView = itemView.findViewById(R.id.anime_name_text_view)
        private val animeDetailText: TextView = itemView.findViewById(R.id.anime_details_text_view)
        private var currentAnimeQuote: AnimeQuote? = null
        init {
            constraintLayout.setOnClickListener {

                currentAnimeQuote?.let { animeQuote ->

                    onClick(animeQuote)
                }
            }
        }

        fun bind(animeQuote: AnimeQuote){
            loadImageURL(imageView, Constants.ANIME_IMG_URL)
            loadText(animeNameText, animeQuote.anime + "-" + animeQuote.character)
            loadText(animeDetailText, animeQuote.quote)
            currentAnimeQuote = animeQuote
        }

        private fun loadImageURL(imageView: ImageView, url: String){
            Picasso.get().load(url).into(imageView)
        }

        private fun loadText(textView: TextView, text: String){
            textView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeQuoteViewModel {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item_view,parent,false)
        return AnimeQuoteViewModel(view,onClick)
    }

    override fun onBindViewHolder(holder: AnimeQuoteViewModel, position: Int) {
        val animeQuote = getItem(position)
        holder.bind(animeQuote)
    }
}

object AnimeQuoteDiffCallback : DiffUtil.ItemCallback<AnimeQuote>(){
    override fun areItemsTheSame(oldItem: AnimeQuote, newItem: AnimeQuote): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AnimeQuote, newItem: AnimeQuote): Boolean {
        return oldItem.anime == newItem.anime &&
                oldItem.quote == newItem.quote &&
                oldItem.character == newItem.character
    }
}