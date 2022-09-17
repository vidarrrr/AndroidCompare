package com.anime.app.repository

import com.anime.app.model.AnimeQuote
import com.anime.app.service.RetrofitHelper
import retrofit2.Response

class AnimeQuoteRepository {
    suspend fun getTenAnimeQuotes(): Response<List<AnimeQuote>>{
        return RetrofitHelper.api.getTenAnimeQuotes()
    }
}