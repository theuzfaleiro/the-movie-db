package theuzfaleiro.github.io.themoviedb.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import theuzfaleiro.github.io.themoviedb.data.network.response.detail.MovieDetail
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.UpcomingMovies

interface TheMovieDbEndpoint {

    @GET("movie/upcoming")
    fun getUpcomingMoviesFromApi(@Query("page") page: Int): Single<UpcomingMovies>

    @GET("search/movie")
    fun searchMovieAtApi(@Query("query") movieName: String): Single<UpcomingMovies>

    @GET("movie/{id}")
    fun getMovieFromApi(@Path("id") id: Int): Single<MovieDetail>
}