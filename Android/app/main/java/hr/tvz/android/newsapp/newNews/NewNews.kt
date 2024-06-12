package hr.tvz.android.newsapp.newNews

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.newsapp.databinding.NewNewsFragmentBinding
import hr.tvz.android.newsapp.model.Kategorija
import hr.tvz.android.newsapp.model.News
import hr.tvz.android.newsapp.newNews.newNewsController.NewNewsController
import hr.tvz.android.newsapp.newNews.newNewsView.NewNewsView
import java.util.Date


class NewNews:   AppCompatActivity(), NewNewsView {
    lateinit var news: News
    lateinit var binding: NewNewsFragmentBinding
    lateinit var newNewsController: NewNewsController
    val ARG_NASLOV = "item_id"
    val TWO_PANE = "bool"

    var bool:Boolean = true
    lateinit var  rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = NewNewsFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        newNewsController = NewNewsController(this)
        newNewsController.getKategorije()
        binding.spremi.setOnClickListener({
            if(binding.naslovInput.text.toString() =="" ||
            binding.tekstInput.text.toString()==""||
            binding.slikaUrl.text.toString()==""){
                showSucces("Unesite podatke")
            }else{
                news = News(binding.naslovInput.text.toString(),
                    binding.tekstInput.text.toString(),
                    binding.slikaUrl.text.toString(),
                    DateFormat.format("yyyy-MM-dd'T'HH:mm:ss", Date()).toString())
                newNewsController.createNews(news,binding.kategorijaSelector.selectedItem.toString())

            }
         })

    }




    override fun showSucces(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ResourceType")
    override fun setItems(kategorija: MutableList<Kategorija>) {
        var kategorijList: ArrayList<String> = ArrayList()
        kategorija.forEach { kategorija ->
            kategorijList.add(kategorija.naziv)
        }
        val spinner = binding.kategorijaSelector
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, kategorijList)
        spinner.setAdapter(adapter)
    }
}