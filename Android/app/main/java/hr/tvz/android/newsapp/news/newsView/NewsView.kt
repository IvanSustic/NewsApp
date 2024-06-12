package hr.tvz.android.newsapp.news.newsView

import hr.tvz.android.newsapp.model.Kategorija
import hr.tvz.android.newsapp.model.News

interface NewsView {
    fun setItems(news: MutableList<News>)
}