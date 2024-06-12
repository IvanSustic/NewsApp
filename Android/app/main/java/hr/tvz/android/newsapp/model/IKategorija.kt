package hr.tvz.android.newsapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IKategorija {


    @GET("all")
    fun dohvatiSveKategorije(): Call<MutableList<Kategorija>>
}