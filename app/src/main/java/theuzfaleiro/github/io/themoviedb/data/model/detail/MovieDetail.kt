package theuzfaleiro.github.io.themoviedb.data.model.detail

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import theuzfaleiro.github.io.themoviedb.data.network.response.detail.MovieDetail

@SuppressLint("ParcelCreator")
@Parcelize
data class MovieDetail(
        val originalLanguage: String? = null,
        val imdbId: String? = null,
        val video: Boolean? = null,
        val title: String? = null,
        val backdropPath: String? = null,
        val revenue: Int? = null,
        val genres: List<Genres?>? = null,
        val popularity: Double? = null,
        val productionCountries: List<ProductionCountries?>? = null,
        val id: Int? = null,
        val voteCount: Int? = null,
        val budget: Int? = null,
        val overview: String? = null,
        val originalTitle: String? = null,
        val runtime: Int? = null,
        val posterPath: String? = null,
        val spokenLanguages: List<SpokenLanguages?>? = null,
        val productionCompanies: List<ProductionCompanies?>? = null,
        val releaseDate: String? = null,
        val voteAverage: Double? = null,
        val tagline: String? = null,
        val adult: Boolean? = null,
        val status: String? = null
) : Parcelable {
    constructor(movieDetail: MovieDetail) : this(
            originalLanguage = movieDetail.originalLanguage,
            imdbId = movieDetail.imdbId,
            video = movieDetail.video,
            title = movieDetail.title,
            backdropPath = movieDetail.backdropPath,
            revenue = movieDetail.revenue,
            genres = movieDetail.genres,
            popularity = movieDetail.popularity,
            productionCompanies = movieDetail.productionCompanies,
            id = movieDetail.id,
            voteCount = movieDetail.voteCount,
            budget = movieDetail.budget,
            overview = movieDetail.overview,
            originalTitle = movieDetail.originalTitle,
            runtime = movieDetail.runtime,
            posterPath = movieDetail.posterPath,
            spokenLanguages = movieDetail.spokenLanguages,
            productionCountries = movieDetail.productionCountries,
            releaseDate = movieDetail.releaseDate,
            voteAverage = movieDetail.voteAverage,
            tagline = movieDetail.tagline,
            adult = movieDetail.adult,
            status = movieDetail.status
    )
}

