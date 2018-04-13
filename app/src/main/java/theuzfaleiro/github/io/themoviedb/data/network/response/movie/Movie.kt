package theuzfaleiro.github.io.themoviedb.data.network.response.movie

import com.squareup.moshi.Json

data class Movie(
        @Json(name = "vote_count") val voteCount: Int,
        @Json(name = "overview") val overview: String,
        @Json(name = "id") val id: Int,
        @Json(name = "video") val video: Boolean,
        @Json(name = "vote_average") val voteAverage: Double,
        @Json(name = "title") val title: String,
        @Json(name = "popularity") val popularity: Double,
        @Json(name = "poster_path") val posterPath: String? = null,
        @Json(name = "original_language") val originalLanguage: String,
        @Json(name = "original_title") val originalTitle: String,
        @Json(name = "genre_ids") val genreIds: List<Int> = listOf(),
        @Json(name = "backdrop_path") val backdropPath: String? = null,
        @Json(name = "adult") val adult: Boolean,
        @Json(name = "release_date") val releaseDate: String
)