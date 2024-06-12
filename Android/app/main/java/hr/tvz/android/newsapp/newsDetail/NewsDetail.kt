package hr.tvz.android.newsapp.newsDetail

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import hr.tvz.android.newsapp.R
import hr.tvz.android.newsapp.model.News
import hr.tvz.android.newsapp.newsDetail.newsDetailController.NewsDetailController
import hr.tvz.android.newsapp.newsDetail.newsDetailView.NewsDetailView
import hr.tvz.android.newsapp.newsWidget.NewsWidget

class NewsDetail: Fragment(), NewsDetailView {
    lateinit var news: News

    lateinit var newsDetailController: NewsDetailController
    val ARG_NASLOV = "item_id"
    val TWO_PANE = "bool"

    var bool:Boolean = true
    lateinit var  rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        Fresco.initialize(context)
        super.onCreate(savedInstanceState)

        newsDetailController = NewsDetailController(this)

        context?.registerReceiver(
            NewsWidget(),
            IntentFilter(NewsWidget().UPDATE_WIDGET),
            Context.RECEIVER_EXPORTED
        )
        println(arguments)
        if(arguments?.containsKey(ARG_NASLOV) == true) {
            requireArguments().getString(ARG_NASLOV)?.let { newsDetailController.dohvatiNews(it) }

        }
        if(arguments?.containsKey(TWO_PANE) == true) {
            bool = requireArguments().getBoolean(TWO_PANE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView= inflater.inflate(R.layout.news_detalji, container, false)


        return rootView
    }

    override fun setItems(news: News) {
        if (news != null) {

            val i = Intent(NewsWidget().UPDATE_WIDGET)
            var arguments = Bundle()
            arguments.putString(NewsWidget().NASLOV, news.naslov)
            arguments.putString(NewsWidget().DETALJI, news.tekst)
            arguments.putString(NewsWidget().SLIKA, news.slika)

            i.putExtras(arguments)
            context?.sendBroadcast(i)
            (rootView.findViewById<View>(R.id.naslov) as TextView).text = news!!.naslov
            (rootView.findViewById<View>(R.id.detalji) as TextView).text = news!!.tekst

            val uri = Uri.parse(news.slika)
            val slika = rootView.findViewById<View>(R.id.slika) as ImageView
            slika.setImageURI(uri)



        }
    }
}