package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie_layout.view.*
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie

class MovieAdapter(private val movieList: List<Movie> = listOf(), private val movieSelected: (movieSelected: Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_layout, parent, false))
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItemsToView(movieList[position], movieSelected)
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val moviePoster = itemView.imageViewMoviePoster

        fun bindItemsToView(movie: Movie, movieSelected: (movieSelected: Movie) -> Unit) {

            Glide.with(moviePoster.context)
                    .load("https://image.tmdb.org/t/p/w780/noU2Im2GcF9TuqJcJUaU2peP5rO.jpg")
                    .into(moviePoster)

            itemView.setOnClickListener {
                movieSelected(movie)
            }
        }
    }

}