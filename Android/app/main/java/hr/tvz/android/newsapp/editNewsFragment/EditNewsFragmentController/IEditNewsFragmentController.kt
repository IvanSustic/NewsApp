package hr.tvz.android.newsapp.editNewsFragment.EditNewsFragmentController

import hr.tvz.android.newsapp.model.News

interface IEditNewsFragmentController {
    fun setitem(news: String)
    fun setKategorije()
   fun createNews(news: News,naslov:String, kategorija: String)

}