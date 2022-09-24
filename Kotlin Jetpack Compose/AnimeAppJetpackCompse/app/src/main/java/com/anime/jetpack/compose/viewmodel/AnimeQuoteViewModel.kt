package com.anime.jetpack.compose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.jetpack.compose.model.AnimeQuote
import com.anime.jetpack.compose.repository.AnimeQuoteRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Response

class AnimeQuoteViewModel(
    private val repository: AnimeQuoteRepository,
    private val onError: (String?) -> Unit
) : ViewModel() {
    val animeQuoteList: MutableLiveData<Response<List<AnimeQuote>>> = MutableLiveData()
    private val handler = CoroutineExceptionHandler { _, error ->
        onError(error.message)
    }

    fun getTenAnimeQuotes() {
        viewModelScope.launch(handler) {
            val response = repository.getTenAnimeQuotes()
            animeQuoteList.value = response
        }
    }
}