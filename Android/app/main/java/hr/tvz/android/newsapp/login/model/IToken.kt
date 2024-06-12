package hr.tvz.android.newsapp.login.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface IToken {

    @POST("login")
    fun getAccessToken(
        @Body user: User

    ): Call<AccessToken?>?

    @POST("logout/{token}")
    fun logout(
        @Path("token") token: String

    ):Call<String>
}