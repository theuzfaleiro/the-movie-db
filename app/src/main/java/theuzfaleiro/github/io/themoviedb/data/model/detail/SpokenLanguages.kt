package theuzfaleiro.github.io.themoviedb.data.model.detail

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class SpokenLanguages(
        val name: String? = null,
        val iso6391: String? = null
) : Parcelable
