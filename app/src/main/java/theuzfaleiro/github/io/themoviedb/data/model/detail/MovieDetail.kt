package theuzfaleiro.github.io.themoviedb.data.model.detail

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import theuzfaleiro.github.io.themoviedb.data.network.response.detail.MovieDetail

@SuppressLint("ParcelCreator")
@Parcelize
data class MovieDetail(
        val originalLanguage: String,
        val title: String,
        val backdropPath: String? = null,
        val genres: List<Genres>,
        val id: Int? = null,
        val budget: Double? = null,
        val overview: String,
        val originalTitle: String,
        val runtime: String,
        val posterPath: String? = null,
        val releaseDate: String,
        val voteAverage: Float,
        val tagline: String
) : Parcelable {
    constructor(movieDetail: MovieDetail) : this(
            originalLanguage = movieDetail.originalLanguage,
            title = movieDetail.title,
            backdropPath = movieDetail.backdropPath,
            genres = movieDetail.genres,
            id = movieDetail.id,
            budget = movieDetail.budget?.toDouble(),
            overview = movieDetail.overview,
            originalTitle = movieDetail.originalTitle,
            runtime = movieDetail.runtime.toString(),
            posterPath = movieDetail.posterPath,
            releaseDate = movieDetail.releaseDate,
            voteAverage = movieDetail.voteAverage.toFloat() / 2,
            tagline = movieDetail.tagline
    )
}

