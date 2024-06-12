package hr.tvz.android.newsapp.news.newsController

import hr.tvz.android.newsapp.ServiceGenerator
import hr.tvz.android.newsapp.categorys.categoryView.CategoryView
import hr.tvz.android.newsapp.model.IKategorija
import hr.tvz.android.newsapp.model.INews
import hr.tvz.android.newsapp.model.Kategorija
import hr.tvz.android.newsapp.model.News
import hr.tvz.android.newsapp.news.newsView.NewsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsController(var newsView: NewsView): INewsController {
    var modeli: MutableList<News> = emptyList<News>().toMutableList()

    val client: INews = ServiceGenerator().createService(
        INews::class.java,
        "http://10.0.2.2:8080/news/"
    )
    override fun dohvatiNews(kategorija: String) {
        val news: Call<MutableList<News>> = client.dohvatiSveNewsPoKategoriji(kategorija)
        news.enqueue(object: Callback<MutableList<News>> {
            override fun onResponse(
                call: Call<MutableList<News>>,
                response: Response<MutableList<News>>
            ) {

                if (response.isSuccessful) {
                    modeli = response.body()!!
                    newsView.setItems(modeli)


                }

            }
            override fun onFailure(call: Call<MutableList<News>>, t: Throwable) {


            }

        })



    }

    override fun dohvatiAllNews() {
        val news: Call<MutableList<News>> = client.dohvatiSveNews()
        news.enqueue(object: Callback<MutableList<News>> {
            override fun onResponse(
                call: Call<MutableList<News>>,
                response: Response<MutableList<News>>
            ) {

                if (response.isSuccessful) {
                    modeli = response.body()!!
                    newsView.setItems(modeli)


                }

            }
            override fun onFailure(call: Call<MutableList<News>>, t: Throwable) {


            }

        })



    }
}