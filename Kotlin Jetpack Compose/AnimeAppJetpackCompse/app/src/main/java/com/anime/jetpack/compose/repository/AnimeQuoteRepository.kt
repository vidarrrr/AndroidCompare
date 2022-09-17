package com.anime.jetpack.compose.repository

import com.anime.jetpack.compose.model.AnimeQuote
import com.anime.jetpack.compose.service.RetrofitHelper
import retrofit2.Response

class AnimeQuoteRepository {
    suspend fun getTenAnimeQuotes(): Response<List<AnimeQuote>>{
        return RetrofitHelper.api.getTenAnimeQuotes()
    }
}