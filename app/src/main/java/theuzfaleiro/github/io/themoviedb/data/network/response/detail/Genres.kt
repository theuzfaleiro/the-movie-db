package theuzfaleiro.github.io.themoviedb.data.network.response.detail

import com.squareup.moshi.Json

data class Genres(
        @Json(name = "name") val name: String,
        @Json(name = "id") val id: Int
)
