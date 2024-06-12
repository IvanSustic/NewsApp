package hr.tvz.android.newsapp.adminNews

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import hr.tvz.android.newsapp.databinding.ActivityAdminNewsBinding
import hr.tvz.android.newsapp.newNews.NewNews
import hr.tvz.android.newsapp.newsEdit.newsEdit

class AdminNews : AppCompatActivity() {
    lateinit var binding: ActivityAdminNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdminNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = Intent(this, NewNews::class.java)
        binding.newNewsButton.setOnClickListener({
            startActivity(intent)
        })
        val intent2 = Intent(this, newsEdit::class.java)
        binding.editNewsButton.setOnClickListener({
            startActivity(intent2)
        })

    }
}