package hr.tvz.android.newsapp.userNews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.newsapp.R
import hr.tvz.android.newsapp.categorys.CategoryFragment
import hr.tvz.android.newsapp.databinding.ActivityUserNewsBinding
import hr.tvz.android.newsapp.news.NewsFragment
import hr.tvz.android.newsapp.newsDetail.NewsDetail

class UserNews : AppCompatActivity(), CategoryFragment.Callbacks, NewsFragment.CallbacksNews{
    lateinit var binding: ActivityUserNewsBinding
    var mTwoPane = false
    var token = "token"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if(binding.itemList2 != null) {
            mTwoPane = true

        } else {
            mTwoPane = false
            var itemListFragment = CategoryFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.item_list_container, itemListFragment)
                .commit()
        }

    }

    override fun onItemSelected(id: String?) {

        var arguments = Bundle()
        arguments.putString(NewsFragment().ARG_ITEM_ID, id)
        arguments.putBoolean(NewsFragment().TWO_PANE, mTwoPane)
        var newsFragment = NewsFragment()
        newsFragment.arguments = arguments
        var fragmentTransaction = supportFragmentManager.beginTransaction()

        if (mTwoPane) {
            fragmentTransaction
                .replace(R.id.item_list2, newsFragment)
                .commit()
        } else {
            fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.item_list_container, newsFragment)
                .commit()
        }
    }

    override fun onItemSelectedNews(id: String?) {

        var arguments = Bundle()
        arguments.putString(NewsDetail().ARG_NASLOV, id)
        arguments.putBoolean(NewsDetail().TWO_PANE, mTwoPane)
        var newsFragment = NewsDetail()
        newsFragment.arguments = arguments
        var fragmentTransaction = supportFragmentManager.beginTransaction()

        if (mTwoPane) {
            fragmentTransaction
                .replace(R.id.item_list2, newsFragment)
                .commit()
        } else {
            fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.item_list_container, newsFragment)
                .commit()
        }
    }
}