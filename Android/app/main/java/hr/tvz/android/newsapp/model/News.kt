package hr.tvz.android.newsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class News(
                var naslov:String,
                var  tekst:String,
                var slika: String,
                var datum:String): Parcelable

