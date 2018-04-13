package theuzfaleiro.github.io.themoviedb.data.network.response.movie

import com.squareup.moshi.Json

data class UpcomingMovies(
        val dates: Dates? = null,
        val page: Int? = null,
        val totalPages: Int? = null,
        @Json(name = "results") val results: List<Movie> = listOf(),
        val totalResults: Int? = null
)