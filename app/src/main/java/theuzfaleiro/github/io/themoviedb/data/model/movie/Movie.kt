package theuzfaleiro.github.io.themoviedb.data.model.movie

data class Movie(
        val overview: String? = null,
        val originalLanguage: String? = null,
        val originalTitle: String? = null,
        val video: Boolean? = null,
        val title: String? = null,
        val genreIds: List<Int?>? = null,
        val posterPath: String? = null,
        val backdropPath: String? = null,
        val releaseDate: String? = null,
        val voteAverage: Int? = null,
        val popularity: Double? = null,
        val id: Int? = null,
        val adult: Boolean? = null,
        val voteCount: Int? = null
)