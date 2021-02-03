package com.dizcoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @field: SerializedName("results")
    var results: List<MovieResponse>
)