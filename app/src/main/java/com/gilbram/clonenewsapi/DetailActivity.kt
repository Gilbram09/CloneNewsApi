package com.gilbram.clonenewsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import coil.size.Scale
import com.gilbram.clonenewsapi.data.ArticlesItem
import com.gilbram.clonenewsapi.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    companion object{
        const val DETAIL_NEWS = "Detail_News"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)

        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        binding.run {
            setContentView(root)
            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title= data.title

            imgToolbar.apply {
                load(data.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = data.description
            }
            txtContent.text= data .content
            txtDate.text = data.publishedAt
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}