package hr.tvz.android.newsapp.newNews.newNewsView

import hr.tvz.android.newsapp.model.Kategorija

interface NewNewsView {
    fun showSucces(string: String)
    fun setItems(kategorija: MutableList<Kategorija>)
}