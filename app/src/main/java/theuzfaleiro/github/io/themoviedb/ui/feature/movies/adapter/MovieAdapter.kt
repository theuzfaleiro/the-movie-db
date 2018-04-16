package theuzfaleiro.github.io.themoviedb.ui.feature.movies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie.view.*
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie
import theuzfaleiro.github.io.themoviedb.ui.feature.common.adapter.ViewType
import theuzfaleiro.github.io.themoviedb.ui.feature.common.adapter.ViewTypeAdapter
import theuzfaleiro.github.io.themoviedb.util.extension.toPosterUrl

class MovieAdapter(private val movieSelected: (movieSelected: Movie) -> Unit) : ViewTypeAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MovieViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        (holder as MovieViewHolder).bindItemsToView(item as Movie, movieSelected)
    }


    inner class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)) {
        private val moviePoster = itemView.imageViewMoviePoster

        fun bindItemsToView(movie: Movie, movieSelected: (movieSelected: Movie) -> Unit) {

            moviePoster.contentDescription = movie.title

            Glide.with(moviePoster.context)
                    .load(movie.posterPath?.toPosterUrl() ?: "")
                    .apply(RequestOptions().placeholder(R.drawable.ic_default_movie_cover)
                            .error(R.drawable.ic_error_movie_cover))
                    .into(moviePoster)

            itemView.setOnClickListener {
                movieSelected(movie)
            }
        }
    }
}