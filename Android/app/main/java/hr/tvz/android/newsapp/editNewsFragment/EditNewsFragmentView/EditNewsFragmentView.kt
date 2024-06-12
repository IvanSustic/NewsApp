package hr.tvz.android.newsapp.editNewsFragment.EditNewsFragmentView

import hr.tvz.android.newsapp.model.Kategorija
import hr.tvz.android.newsapp.model.News

interface EditNewsFragmentView {
    fun setKategorije(kategorija: MutableList<Kategorija>)
    fun setItems(news: News)
    fun showSucces(string: String)
}