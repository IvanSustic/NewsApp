package hr.tvz.android.newsapp.editNewsFragment

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import hr.tvz.android.newsapp.R
import hr.tvz.android.newsapp.editNewsFragment.EditNewsFragmentController.EditNewsFragmentController
import hr.tvz.android.newsapp.editNewsFragment.EditNewsFragmentView.EditNewsFragmentView
import hr.tvz.android.newsapp.model.Kategorija
import hr.tvz.android.newsapp.model.News
import hr.tvz.android.newsapp.newsDetail.newsDetailController.NewsDetailController
import hr.tvz.android.newsapp.newsDetail.newsDetailView.NewsDetailView
import hr.tvz.android.newsapp.newsWidget.NewsWidget
import java.util.Date

class EditNewsFragment: Fragment(), EditNewsFragmentView {
    lateinit var news: News

    lateinit var editNewsFragmentController: EditNewsFragmentController
    var ARG_NASLOV = "item_id"
    val TWO_PANE = "bool"

    var bool:Boolean = true
    lateinit var  rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        Fresco.initialize(context)
        super.onCreate(savedInstanceState)

        editNewsFragmentController = EditNewsFragmentController(this)
        editNewsFragmentController.setKategorije()
        if(arguments?.containsKey(ARG_NASLOV) == true) {

            requireArguments().getString(ARG_NASLOV)?.let { editNewsFragmentController.setitem(it);
            ARG_NASLOV = it}

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
        rootView= inflater.inflate(R.layout.new_news_fragment, container, false)
        rootView.findViewById<Button>(R.id.spremi).setOnClickListener({
            if(rootView.findViewById<EditText>(R.id.naslovInput).text.toString() =="" ||
                rootView.findViewById<EditText>(R.id.tekstInput).text.toString()==""||
                rootView.findViewById<EditText>(R.id.slikaUrl).text.toString()==""){
                showSucces("Unesite podatke")
            }else{
                news = News(rootView.findViewById<EditText>(R.id.naslovInput).text.toString(),
                    rootView.findViewById<EditText>(R.id.tekstInput).text.toString(),
                    rootView.findViewById<EditText>(R.id.slikaUrl).text.toString(),
                    DateFormat.format("yyyy-MM-dd'T'HH:mm:ss", Date()).toString())
                editNewsFragmentController.createNews(news,ARG_NASLOV,
                    rootView.findViewById<Spinner>(R.id.kategorijaSelector).selectedItem.toString())

            }
        })

        return rootView
    }

    override fun setKategorije(kategorija: MutableList<Kategorija>) {
        var kategorijList: ArrayList<String> = ArrayList()
        kategorija.forEach { kategorija ->
            kategorijList.add(kategorija.naziv)
        }
        val spinner = rootView.findViewById<Spinner>(R.id.kategorijaSelector)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, kategorijList)
        spinner.setAdapter(adapter)
    }


    override fun setItems(news: News) {
        if (news != null) {

            (rootView.findViewById<View>(R.id.naslovInput) as TextView).text = news!!.naslov
            (rootView.findViewById<View>(R.id.tekstInput) as TextView).text = news!!.tekst
            (rootView.findViewById<View>(R.id.slikaUrl) as TextView).text = news!!.slika
            (rootView.findViewById<View>(R.id.kategorijaSelector) as Spinner).setSelection(1)

        }
    }

    override fun showSucces(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}