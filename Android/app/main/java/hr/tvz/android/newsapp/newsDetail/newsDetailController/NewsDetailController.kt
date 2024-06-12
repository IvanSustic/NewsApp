package hr.tvz.android.newsapp.newsDetail.newsDetailController

import hr.tvz.android.newsapp.ServiceGenerator
import hr.tvz.android.newsapp.model.INews
import hr.tvz.android.newsapp.model.News
import hr.tvz.android.newsapp.newsDetail.newsDetailView.NewsDetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDetailController(var newsDetailView: NewsDetailView ): INewsDetailController {
    lateinit var modeli: News

    override fun dohvatiNews(string: String){
        val client: INews = ServiceGenerator().createService(
            INews::class.java,
            "http://10.0.2.2:8080/news/"
        )
        val news: Call<News> = client.dohvatiNews(string)
        news.enqueue(object : Callback<News> {
            override fun onResponse(
                call: Call<News>,
                response: Response<News>
            ) {
                if (response.isSuccessful) {

                    modeli = response.body()!!
                    newsDetailView.setItems(modeli)
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {

            }

        })

    }


}