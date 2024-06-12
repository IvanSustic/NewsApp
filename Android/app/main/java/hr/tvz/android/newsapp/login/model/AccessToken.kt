package hr.tvz.android.newsapp.login.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccessToken(

    var accessToken: String,
    var token: String

): Parcelable
