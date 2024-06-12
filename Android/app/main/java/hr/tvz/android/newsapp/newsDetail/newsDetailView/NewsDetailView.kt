package hr.tvz.android.newsapp.newsDetail.newsDetailView

import hr.tvz.android.newsapp.model.News

interface NewsDetailView {
    fun setItems(news: News)
}