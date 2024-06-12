package hr.tvz.android.newsapp.news

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import hr.tvz.android.newsapp.categorys.categoryController.CategoryController
import hr.tvz.android.newsapp.categorys.categoryView.CategoryView
import hr.tvz.android.newsapp.model.Kategorija
import hr.tvz.android.newsapp.model.News
import hr.tvz.android.newsapp.news.newsController.NewsController
import hr.tvz.android.newsapp.news.newsView.NewsView
import hr.tvz.android.newsapp.userNews.UserNews

class NewsFragment : ListFragment(), NewsView  {
    var ARG_ITEM_ID = "All"
    val TWO_PANE = "bool"
    lateinit var newsController: NewsController
    private val STATE_ACTIVATED_POSITION = "activated_position"
    var mActivatedPosition = ListView.INVALID_POSITION
    lateinit var todoList:MutableList<News>
    interface CallbacksNews {
        fun onItemSelectedNews(id: String?)
    }

    private val sDummyCallbacks: CallbacksNews =
        object : CallbacksNews {
            override fun onItemSelectedNews(id: String?) {}
        }

    var mCallbacks: CallbacksNews = sDummyCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsController = NewsController(this);
        if(arguments?.containsKey(ARG_ITEM_ID) == true) {
           requireArguments().getString(ARG_ITEM_ID)?.let { ARG_ITEM_ID = it }

        }


        if (ARG_ITEM_ID == "All" ){
            newsController.dohvatiAllNews()
        }else{
            newsController.dohvatiNews(ARG_ITEM_ID)
        }



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Restore the previously serialized activated item position
        if(savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION))
        }
    }

    private fun setActivatedPosition(position: Int) {
        if (position == ListView.INVALID_POSITION) {
            listView.setItemChecked(mActivatedPosition, false)
        } else {
            listView.setItemChecked(position, true)
        }

        mActivatedPosition = position
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Activities containing this fragment must implement its callbacks
        check(context is CallbacksNews) { "Activity must implement fragment's callbacks." }

        mCallbacks = context
    }

    override fun onDetach() {
        super.onDetach()
        mCallbacks = sDummyCallbacks
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

        mCallbacks.onItemSelectedNews(todoList.get(position).naslov)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition)
        }
    }

    fun setActivateOnItemClick(activateOnItemClick: Boolean) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        listView.choiceMode =
            if (activateOnItemClick) ListView.CHOICE_MODE_SINGLE else ListView.CHOICE_MODE_NONE
    }

    override fun setItems(news: MutableList<News>) {
        todoList = news
        var news: MutableList<String> = arrayListOf()
        todoList.forEach { news1 -> news.add(news1.naslov) }
        listAdapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_list_item_activated_1,
            android.R.id.text1,
            news)

    }
}