package theuzfaleiro.github.io.themoviedb.util.extension

private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

fun String.toPosterUrl(): String {
    return POSTER_URL + this
}

fun String.toBackdropUrl(): String {
    return BACKDROP_URL + this
}