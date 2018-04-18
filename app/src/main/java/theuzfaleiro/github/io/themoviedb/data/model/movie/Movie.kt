package theuzfaleiro.github.io.themoviedb.data.model.movie

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.Movie
import theuzfaleiro.github.io.themoviedb.ui.feature.common.adapter.AdapterConstants
import theuzfaleiro.github.io.themoviedb.ui.feature.common.adapter.ViewType

@SuppressLint("ParcelCreator")
@Parcelize
data class Movie(
        val overview: String,
        val id: Int,
        val title: String,
        val posterPath: String? = null,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Int> = listOf(),
        val backdropPath: String? = null,
        val releaseDate: String
) : ViewType, Parcelable {
    override fun getViewType() = AdapterConstants.MOVIE

    constructor(movie: Movie) : this(
            overview = movie.overview,
            id = movie.id,
            title = movie.title,
            posterPath = movie.posterPath,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            genreIds = movie.genreIds,
            backdropPath = movie.backdropPath,
            releaseDate = movie.releaseDate
    )
}