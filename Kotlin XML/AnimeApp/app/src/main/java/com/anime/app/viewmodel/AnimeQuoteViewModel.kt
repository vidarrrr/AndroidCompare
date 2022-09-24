package com.anime.app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.app.model.AnimeQuote
import com.anime.app.repository.AnimeQuoteRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Response

class AnimeQuoteViewModel(private val repository: AnimeQuoteRepository,private val onError: (String?)->Unit) : ViewModel() {
    val animeQuoteList: MutableLiveData<Response<List<AnimeQuote>>> = MutableLiveData()
    //https://stackoverflow.com/questions/58180265/coroutines-to-call-a-rest-api-fatal-exception-main
    private val handler = CoroutineExceptionHandler{ _, error ->
        //error.message?.let { Log.d("error", it) }
        onError(error.message)
    }

    fun getTenAnimeQuotes(){
        viewModelScope.launch(handler) {
            val response = repository.getTenAnimeQuotes()
            animeQuoteList.value = response
        }
    }
}