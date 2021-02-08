package com.rjt.animecharacters

import com.google.gson.annotations.SerializedName

data class Result(
    val airing: Boolean,
    val end_date: String,
    val episodes: Int,
    val image_url: String,
    val mal_id: Int,
    val members: Int,
    val rated: String,
    val score: Double,
    val start_date: String,
    val synopsis: String,
    val title: String,
    val type: String,
    val url: String
)
data class ResultList(
    @SerializedName("results")
    val resultList: List<Result>)