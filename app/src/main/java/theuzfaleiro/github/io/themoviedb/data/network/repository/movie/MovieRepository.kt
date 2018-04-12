package theuzfaleiro.github.io.themoviedb.data.network.repository.movie

import io.reactivex.Single
import theuzfaleiro.github.io.themoviedb.data.model.movie.UpcomingMovies
import theuzfaleiro.github.io.themoviedb.data.network.TheMovieDbEndpoint

open class MovieRepository(private val theMovieDbEndpoint: TheMovieDbEndpoint) {

    open fun getMoviesFromApi(page: Int): Single<UpcomingMovies> {
        return theMovieDbEndpoint.getUpcomingMoviesFromApi(page)
    }
}