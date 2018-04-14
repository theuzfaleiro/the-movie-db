package theuzfaleiro.github.io.themoviedb.data.network.repository.movie

import io.reactivex.Single
import theuzfaleiro.github.io.themoviedb.data.network.TheMovieDbEndpoint
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.Movie
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.UpcomingMovies

open class MovieRepository(private val theMovieDbEndpoint: TheMovieDbEndpoint) {

    open fun getMoviesFromApi(page: Int): Single<UpcomingMovies> {
        return theMovieDbEndpoint.getUpcomingMoviesFromApi(page)
    }

    open fun searchMovieAtApi(movieName: String): Single<UpcomingMovies> {
        return theMovieDbEndpoint.searchMovieAtApi(movieName)
    }

}