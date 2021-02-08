package com.rjt.animecharacters

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GetAnimeCharacters {
    @GET("anime")
    fun getAnime(
        @Query("q") query: String
    ) : Single<ResultList>
}