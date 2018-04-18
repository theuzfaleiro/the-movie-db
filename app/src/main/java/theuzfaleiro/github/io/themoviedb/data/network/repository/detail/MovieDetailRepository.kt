package theuzfaleiro.github.io.themoviedb.data.network.repository.detail

import io.reactivex.Single
import theuzfaleiro.github.io.themoviedb.data.network.TheMovieDbEndpoint
import theuzfaleiro.github.io.themoviedb.data.network.response.detail.MovieDetail

open class MovieDetailRepository(private val theMovieDbEndpoint: TheMovieDbEndpoint) {

    open fun getMoviesFromApi(movieId: Int): Single<MovieDetail> {
        return theMovieDbEndpoint.getMovieFromApi(movieId)
    }
}