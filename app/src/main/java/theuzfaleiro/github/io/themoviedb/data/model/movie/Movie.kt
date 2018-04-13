package theuzfaleiro.github.io.themoviedb.data.model.movie

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.Movie

@SuppressLint("ParcelCreator")
@Parcelize
data class Movie(
        val voteCount: Int,
        val overview: String,
        val id: Int,
        val video: Boolean,
        val voteAverage: Double,
        val title: String,
        val popularity: Double,
        val posterPath: String? = null,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Int> = listOf(),
        val backdropPath: String? = null,
        val adult: Boolean,
        val releaseDate: String
) : Parcelable {
    constructor(movie: Movie) : this(
            voteCount = movie.voteCount,
            overview = movie.overview,
            id = movie.id,
            video = movie.video,
            voteAverage = movie.voteAverage,
            title = movie.title,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            genreIds = movie.genreIds,
            backdropPath = movie.backdropPath,
            adult = movie.adult,
            releaseDate = movie.releaseDate
    )
}