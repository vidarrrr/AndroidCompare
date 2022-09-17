package com.anime.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.jetpack.compose.model.AnimeQuote
import com.anime.jetpack.compose.repository.AnimeQuoteRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class AnimeQuoteViewModel(private val repository: AnimeQuoteRepository) : ViewModel() {
    val animeQuoteList: MutableLiveData<Response<List<AnimeQuote>>> = MutableLiveData()


    fun getTenAnimeQuotes(){
        viewModelScope.launch {
            val response = repository.getTenAnimeQuotes()
            animeQuoteList.value = response
        }
    }
}