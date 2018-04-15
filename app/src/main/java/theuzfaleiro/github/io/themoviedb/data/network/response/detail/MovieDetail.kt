package theuzfaleiro.github.io.themoviedb.data.network.response.detail

import com.squareup.moshi.Json
import theuzfaleiro.github.io.themoviedb.data.model.detail.Genres
import theuzfaleiro.github.io.themoviedb.data.model.detail.ProductionCompanies
import theuzfaleiro.github.io.themoviedb.data.model.detail.ProductionCountries
import theuzfaleiro.github.io.themoviedb.data.model.detail.SpokenLanguages

data class MovieDetail(
        @Json(name = "adult") val adult: Boolean,
        @Json(name = "backdrop_path") val backdropPath: String? = null,
        @Json(name = "budget") val budget: Int,
        @Json(name = "genres") val genres: List<Genres> = listOf(),
        @Json(name = "id") val id: Int,
        @Json(name = "imdb_id") val imdbId: String? = null,
        @Json(name = "original_language") val originalLanguage: String,
        @Json(name = "original_title") val originalTitle: String,
        @Json(name = "overview") val overview: String,
        @Json(name = "popularity") val popularity: Double,
        @Json(name = "poster_path") val posterPath: String? = null,
        @Json(name = "production_companies") val productionCompanies: List<ProductionCompanies> = listOf(),
        @Json(name = "production_countries") val productionCountries: List<ProductionCountries> = listOf(),
        @Json(name = "release_date") val releaseDate: String,
        @Json(name = "revenue") val revenue: Int,
        @Json(name = "runtime") val runtime: Int,
        @Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguages> = listOf(),
        @Json(name = "status") val status: String,
        @Json(name = "tagline") val tagline: String,
        @Json(name = "title") val title: String,
        @Json(name = "video") val video: Boolean,
        @Json(name = "vote_average") val voteAverage: Double,
        @Json(name = "vote_count") val voteCount: Int? = null
)
