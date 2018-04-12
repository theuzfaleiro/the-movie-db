package theuzfaleiro.github.io.themoviedb.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie
import theuzfaleiro.github.io.themoviedb.data.model.movie.UpcomingMovies

interface TheMovieDbEndpoint {

    @GET("genre/movie/list")
    fun getMovieGenresFromApi(): Single<Movie>

    @GET("movie/upcoming")
    fun getUpcomingMoviesFromApi(@Query("page") page: Int): Single<UpcomingMovies>

    @GET("movie/{id}")
    fun getMovieFromApi(@Path("id") id: Int): Single<Movie>
}