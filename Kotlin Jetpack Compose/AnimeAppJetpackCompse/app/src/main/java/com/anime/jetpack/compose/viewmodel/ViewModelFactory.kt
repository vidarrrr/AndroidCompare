package com.anime.jetpack.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anime.jetpack.compose.repository.AnimeQuoteRepository
import com.anime.app.viewmodel.AnimeQuoteViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: AnimeQuoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AnimeQuoteViewModel::class.java)){
            return AnimeQuoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}