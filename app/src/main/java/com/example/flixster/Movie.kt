package com.example.flixster

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import org.json.JSONArray

@Parcelize
// represents one movie object to display in UI
data class Movie(
    val movieId: Int,
    val voteAverage: Double,
    private val posterPath: String,
    val title: String,
    val overview: String
    ): Parcelable {

    @IgnoredOnParcel
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
                        movieJson.getDouble("vote_average"),
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