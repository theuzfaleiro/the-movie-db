package theuzfaleiro.github.io.themoviedb.data.model.detail

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ProductionCountries(
	val iso31661: String? = null,
	val name: String? = null
) : Parcelable
