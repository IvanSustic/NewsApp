package hr.tvz.android.newsapp.categorys.categoryView

import hr.tvz.android.newsapp.model.Kategorija

interface CategoryView {
    fun setItems(kategorija: MutableList<Kategorija>)
}