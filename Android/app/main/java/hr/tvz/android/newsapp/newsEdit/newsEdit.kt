package hr.tvz.android.newsapp.newsEdit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.tvz.android.newsapp.R
import hr.tvz.android.newsapp.categorys.CategoryFragment
import hr.tvz.android.newsapp.databinding.ActivityNewsEditBinding
import hr.tvz.android.newsapp.databinding.ActivityUserNewsBinding
import hr.tvz.android.newsapp.editNewsFragment.EditNewsFragment
import hr.tvz.android.newsapp.news.NewsFragment
import hr.tvz.android.newsapp.newsDetail.NewsDetail

class newsEdit : AppCompatActivity(),  NewsFragment.CallbacksNews {
    lateinit var binding: ActivityNewsEditBinding
    var mTwoPane = false
    var token = "token"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if(binding.itemDetailContainer != null) {
            mTwoPane = true

        } else {
            mTwoPane = false
            var itemListFragment = NewsFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.item_list_container, itemListFragment)
                .commit()
        }

    }


    override fun onItemSelectedNews(id: String?) {

        var arguments = Bundle()
        arguments.putString(EditNewsFragment().ARG_NASLOV, id)
        arguments.putBoolean(EditNewsFragment().TWO_PANE, mTwoPane)
        var newsFragment = EditNewsFragment()
        newsFragment.arguments = arguments
        var fragmentTransaction = supportFragmentManager.beginTransaction()

        if (mTwoPane) {
            fragmentTransaction
                .replace(R.id.item_detail_container, newsFragment)
                .commit()
        } else {
            fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.item_list_container, newsFragment)
                .commit()
        }
    }
}