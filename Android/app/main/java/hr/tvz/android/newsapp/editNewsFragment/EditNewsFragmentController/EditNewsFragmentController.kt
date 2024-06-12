package hr.tvz.android.newsapp.editNewsFragment.EditNewsFragmentController

import hr.tvz.android.newsapp.ServiceGenerator
import hr.tvz.android.newsapp.editNewsFragment.EditNewsFragmentView.EditNewsFragmentView
import hr.tvz.android.newsapp.model.IKategorija
import hr.tvz.android.newsapp.model.INews
import hr.tvz.android.newsapp.model.Kategorija
import hr.tvz.android.newsapp.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditNewsFragmentController(var editNewsFragmentView: EditNewsFragmentView): IEditNewsFragmentController {
    lateinit var modeli: News
    var kategorijeModel: MutableList<Kategorija> = emptyList<Kategorija>().toMutableList()
    override fun setitem(news: String) {
        val client: INews = ServiceGenerator().createService(
            INews::class.java,
            "http://10.0.2.2:8080/news/"
        )
        val news: Call<News> = client.dohvatiNews(news)
        news.enqueue(object : Callback<News> {
            override fun onResponse(
                call: Call<News>,
                response: Response<News>
            ) {
                if (response.isSuccessful) {

                    modeli = response.body()!!
                    editNewsFragmentView.setItems(modeli)
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {

            }

        })

    }

    override fun setKategorije() {
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
                    kategorijeModel = response.body()!!

                    editNewsFragmentView.setKategorije(kategorijeModel)


                }

            }
            override fun onFailure(call: Call<MutableList<Kategorija>>, t: Throwable) {


            }

        })
    }

    override fun createNews(news: News, naslov:String, kategorija: String) {
        val client: INews = ServiceGenerator().createService(
            INews::class.java,
            "http://10.0.2.2:8080/news/"
        )
        val newsC: Call<News> = client.changeNews(news,naslov,kategorija)
        newsC.enqueue(object : Callback<News> {
            override fun onResponse(
                call: Call<News>,
                response: Response<News>
            ) {
                if (response.isSuccessful) {
                    editNewsFragmentView.showSucces("Uspješno dodana vijest")


                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                editNewsFragmentView.showSucces("Neuspješno dodavanje")
            }

        })
    }


}