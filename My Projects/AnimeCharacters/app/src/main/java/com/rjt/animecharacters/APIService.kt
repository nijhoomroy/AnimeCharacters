package com.rjt.animecharacters

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIService {

    companion object {
        private const val BASE_URL = "https://api.jikan.moe/v3/search/"
        fun getRepo() : Retrofit {
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL).build()
        }
    }
}