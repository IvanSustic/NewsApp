package hr.tvz.android.newsapp.newNews.newNewsController

import hr.tvz.android.newsapp.model.News

interface INewNewsController {
    fun createNews(news: News, kategorija: String)
    fun getKategorije()
}