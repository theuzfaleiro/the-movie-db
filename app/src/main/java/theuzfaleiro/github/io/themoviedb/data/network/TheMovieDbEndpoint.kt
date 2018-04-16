package theuzfaleiro.github.io.themoviedb.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import theuzfaleiro.github.io.themoviedb.data.network.response.detail.MovieDetail
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.Movie
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.UpcomingMovies

interface TheMovieDbEndpoint {

    companion object {
        const val URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "89a8f938241ef5ab367029cc715b5f1a"
        const val DEFAULT_LANGUAGE = "pt-BR"
        const val DEFAULT_REGION = "BR"
    }

    @GET("genre/movie/list")
    fun getMovieGenresFromApi(): Single<Movie>

    @GET("movie/upcoming")
    fun getUpcomingMoviesFromApi(@Query("page") page: Int): Single<UpcomingMovies>

    @GET("search/movie")
    fun searchMovieAtApi(@Query("query") movieName: String): Single<UpcomingMovies>

    @GET("movie/{id}")
    fun getMovieFromApi(@Path("id") id: Int, @Query("api_key") apiKey: String = API_KEY): Single<MovieDetail>
}