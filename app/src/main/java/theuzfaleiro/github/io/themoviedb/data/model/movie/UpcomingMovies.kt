package theuzfaleiro.github.io.themoviedb.data.model.movie

data class UpcomingMovies(
        val dates: Dates? = null,
        val page: Int? = null,
        val totalPages: Int? = null,
        val results: List<Movie> = listOf(),
        val totalResults: Int? = null
)