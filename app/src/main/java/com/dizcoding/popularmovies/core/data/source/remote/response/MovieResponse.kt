package com.dizcoding.popularmovies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @field:SerializedName("adult")
    var adult : Boolean?,
    @field:SerializedName("backdrop_path")
    var backdrop_path : String?,
    @field:SerializedName("id")
    var id : Int?,
    @field:SerializedName("original_language")
    var original_language : String?,
    @field:SerializedName("original_title")
    var original_title : String?,
    @field:SerializedName("overview")
    var overview : String?,
    @field:SerializedName("popularity")
    var popularity : Double?,
    @field:SerializedName("poster_path")
    var poster_path : String?,
    @field:SerializedName("release_date")
    var release_date : String?,
    @field:SerializedName("title")
    var title : String?,
    @field:SerializedName("video")
    var video : Boolean?,
    @field:SerializedName("vote_average")
    var vote_average : Double?,
    @field:SerializedName("vote_count")
    var vote_count : Int?
)