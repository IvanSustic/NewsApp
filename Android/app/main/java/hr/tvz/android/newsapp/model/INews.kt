package hr.tvz.android.newsapp.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface INews {
    @GET("all")
    fun dohvatiSveNews(): Call<MutableList<News>>
    @GET("kategorija/{kategorija}")
    fun dohvatiSveNewsPoKategoriji(@Path("kategorija") kategorija: String): Call<MutableList<News>>
    @GET("{naslov}")
    fun dohvatiNews(@Path("naslov") naslov: String): Call<News>
    @POST("add/{kategorija}")
    fun createNews(@Body newsDTO: News, @Path("kategorija") kategorija: String): Call<News>
    @PUT("change/{naslov}/{kategorije}")
    fun changeNews(@Body newsDTO: News, @Path("naslov") naslov: String, @Path("kategorije") kategorije: String): Call<News>

}