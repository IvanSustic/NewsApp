package hr.tvz.android.newsapp.newNews.newNewsController

import hr.tvz.android.newsapp.ServiceGenerator
import hr.tvz.android.newsapp.model.IKategorija
import hr.tvz.android.newsapp.model.INews
import hr.tvz.android.newsapp.model.Kategorija
import hr.tvz.android.newsapp.model.News
import hr.tvz.android.newsapp.newNews.newNewsView.NewNewsView
import hr.tvz.android.newsapp.newsDetail.newsDetailController.INewsDetailController
import hr.tvz.android.newsapp.newsDetail.newsDetailView.NewsDetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewNewsController(var newNewsView: NewNewsView): INewNewsController {
    var modeli: MutableList<Kategorija> = emptyList<Kategorija>().toMutableList()

    override fun createNews(news: News, kategorija: String) {
        val client: INews = ServiceGenerator().createService(
            INews::class.java,
            "http://10.0.2.2:8080/news/"
        )
        val newsC: Call<News> = client.createNews(news,kategorija)
        newsC.enqueue(object : Callback<News> {
            override fun onResponse(
                call: Call<News>,
                response: Response<News>
            ) {
                if (response.isSuccessful) {
                    newNewsView.showSucces("Uspješno dodana vijest")


                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                newNewsView.showSucces("Neuspješno dodavanje")
            }

        })

    }

    override fun getKategorije() {
        val client: IKategorija = ServiceGenerator().createService(
            IKategorija::class.java,
            "http://10.0.2.2:8080/kategorija/"
        )
        val kategorije: Call<MutableList<Kategorija>> = client.dohvatiSveKategorije()
        kategorije.enqueue(object: Callback<MutableList<Kategorija>> {
            override fun onResponse(
                call: Call<MutableList<Kategorija>>,
                response: Response<MutableList<Kategorija>>
            ) {

                if (response.isSuccessful) {
                    modeli = response.body()!!

                    newNewsView.setItems(modeli)
                    println(modeli)

                }

            }
            override fun onFailure(call: Call<MutableList<Kategorija>>, t: Throwable) {


            }

        })

    }
}