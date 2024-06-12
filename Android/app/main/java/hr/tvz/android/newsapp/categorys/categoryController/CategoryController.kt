package hr.tvz.android.newsapp.categorys.categoryController

import hr.tvz.android.newsapp.ServiceGenerator
import hr.tvz.android.newsapp.categorys.categoryView.CategoryView
import hr.tvz.android.newsapp.model.IKategorija
import hr.tvz.android.newsapp.model.Kategorija
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryController(var categoryView: CategoryView): ICategoryController{
    var modeli: MutableList<Kategorija> = emptyList<Kategorija>().toMutableList()

    val client: IKategorija = ServiceGenerator().createService(
        IKategorija::class.java,
        "http://10.0.2.2:8080/kategorija/"
    )
    override fun dohvatiKategorije() {
        val kategorije: Call<MutableList<Kategorija>> = client.dohvatiSveKategorije()
        kategorije.enqueue(object: Callback<MutableList<Kategorija>> {
            override fun onResponse(
                call: Call<MutableList<Kategorija>>,
                response: Response<MutableList<Kategorija>>
            ) {

                if (response.isSuccessful) {
                    modeli = response.body()!!

                    categoryView.setItems(modeli)
                    println(modeli)

                }

            }
            override fun onFailure(call: Call<MutableList<Kategorija>>, t: Throwable) {


            }

        })





    }
}