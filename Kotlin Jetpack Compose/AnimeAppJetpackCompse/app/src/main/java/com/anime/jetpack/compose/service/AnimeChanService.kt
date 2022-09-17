package com.anime.jetpack.compose.service

import com.anime.jetpack.compose.model.AnimeQuote
import retrofit2.Response
import retrofit2.http.GET

interface AnimeChanService {

    @GET("api/quotes")
    suspend fun getTenAnimeQuotes() : Response<List<AnimeQuote>>

}