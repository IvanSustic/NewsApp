package hr.tvz.android.newsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kategorija(
                      var naziv:String
                      ): Parcelable
