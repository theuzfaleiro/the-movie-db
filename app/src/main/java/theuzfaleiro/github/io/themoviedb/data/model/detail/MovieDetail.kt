package theuzfaleiro.github.io.themoviedb.data.model.detail

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import theuzfaleiro.github.io.themoviedb.data.network.response.detail.MovieDetail

@SuppressLint("ParcelCreator")
@Parcelize
data class MovieDetail(
        val originalLanguage: String,
        val imdbId: String? = null,
        val video: Boolean,
        val title: String,
        val backdropPath: String? = null,
        val revenue: Int,
        val genres: List<Genres>,
        val popularity: Double,
        val productionCountries: List<ProductionCountries>,
        val id: Int,
        val voteCount: Int? = null,
        val budget: Double,
        val overview: String,
        val originalTitle: String,
        val runtime: String,
        val posterPath: String? = null,
        val spokenLanguages: List<SpokenLanguages>,
        val productionCompanies: List<ProductionCompanies>,
        val releaseDate: String,
        val voteAverage: Float,
        val tagline: String,
        val adult: Boolean,
        val status: String
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
            budget = movieDetail.budget.toDouble(),
            overview = movieDetail.overview,
            originalTitle = movieDetail.originalTitle,
            runtime = movieDetail.runtime.toString(),
            posterPath = movieDetail.posterPath,
            spokenLanguages = movieDetail.spokenLanguages,
            productionCountries = movieDetail.productionCountries,
            releaseDate = movieDetail.releaseDate,
            voteAverage = movieDetail.voteAverage.toFloat(),
            tagline = movieDetail.tagline,
            adult = movieDetail.adult,
            status = movieDetail.status
    )
}

