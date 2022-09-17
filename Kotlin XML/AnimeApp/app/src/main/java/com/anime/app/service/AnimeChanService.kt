package com.anime.app.service

import com.anime.app.model.AnimeQuote
import retrofit2.Response
import retrofit2.http.GET

interface AnimeChanService {

    @GET("api/quotes")
    suspend fun getTenAnimeQuotes() : Response<List<AnimeQuote>>

}