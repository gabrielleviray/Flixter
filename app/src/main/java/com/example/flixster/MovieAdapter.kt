package com.example.flixster

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
// this file is the glue between the recycler views and underylying dataset
private const val TAG = "MovieAdapter"
const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MovieAdapter(private val context: Context, private val moviesList: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie = moviesList[position]
        holder.bind(movie)
    }

    override fun getItemCount() = moviesList.size


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        // register the click listener
        init {
            // every time we create new view holder
            // this refers to the class and class is implementing interface
            itemView.setOnClickListener(this)
        }

        fun bind(movie:Movie){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }

        //  goal: 2 things
        override fun onClick(p0: View?) {
            // 1. get notified of specific movie that was clicked on
            // adapterPosition tells us what is the position of this view holder among the data set
            val movie = moviesList[adapterPosition]

            // visual indicator
//            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()

            // 2. use intent to navigate to a new activity(screen)
            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("movie_title", movie.title) // pass each attribute one at a time
            intent.putExtra("MOVIE_EXTRA", movie)
            context.startActivity(intent)

        }
    }

}
