package hr.tvz.android.newsapp.login.model

import android.os.Parcelable
import hr.tvz.android.newsapp.model.Kategorija
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class User (    var username:String,
    var  password:String,
): Parcelable