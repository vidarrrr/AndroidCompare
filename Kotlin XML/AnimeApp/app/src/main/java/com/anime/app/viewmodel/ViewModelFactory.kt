package com.anime.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anime.app.repository.AnimeQuoteRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: AnimeQuoteRepository, private val onError: (String?)->Unit):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AnimeQuoteViewModel::class.java)){
            return AnimeQuoteViewModel(repository,onError) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}