package theuzfaleiro.github.io.themoviedb.util.extension

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun Double.toMoneyDisplay() =
        (NumberFormat.getCurrencyInstance(Locale("en", "US")) as DecimalFormat).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
            positivePrefix = "U$ "
            negativePrefix = "U$ -"
        }.format(this)