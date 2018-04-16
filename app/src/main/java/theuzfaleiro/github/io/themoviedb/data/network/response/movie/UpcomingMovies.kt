package theuzfaleiro.github.io.themoviedb.data.network.response.movie

import com.squareup.moshi.Json

data class UpcomingMovies(
        @Json(name = "results") val results: List<Movie> = listOf()
)