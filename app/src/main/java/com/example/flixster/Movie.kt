package com.example.flixster

import org.json.JSONArray

// represents one movie object to display in UI
data class Movie(
    val movieId: Int,
    private val posterPath: String,
    val title: String,
    val overview: String
    ){

    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object {
        // function definition to call this function without having an instance of Movie
        fun fromJsonArray(movieArr: JSONArray): List<Movie> {
            val moviesList = mutableListOf<Movie>()
            for (i in 0 until movieArr.length()){
                val movieJson = movieArr.getJSONObject(i)
                moviesList.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview")
                    )
                )
            }
            return moviesList
        }
    }
}