package theuzfaleiro.github.io.themoviedb.data.network.response.detail

import com.squareup.moshi.Json
import theuzfaleiro.github.io.themoviedb.data.model.detail.Genres

data class MovieDetail(
        @Json(name = "backdrop_path") val backdropPath: String? = null,
        @Json(name = "budget") val budget: Int? = null,
        @Json(name = "genres") val genres: List<Genres> = listOf(),
        @Json(name = "id") val id: Int? = null,
        @Json(name = "original_language") val originalLanguage: String,
        @Json(name = "original_title") val originalTitle: String,
        @Json(name = "overview") val overview: String,
        @Json(name = "poster_path") val posterPath: String? = null,
        @Json(name = "release_date") val releaseDate: String,
        @Json(name = "runtime") val runtime: Int? = null,
        @Json(name = "tagline") val tagline: String,
        @Json(name = "title") val title: String,
        @Json(name = "vote_average") val voteAverage: Double
)
