package com.gilbram.clonenewsapi.data


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.gilbram.clonenewsapi.DetailActivity
import com.gilbram.clonenewsapi.databinding.CdvNewsHeadlineBinding


class CdvNewsHeadlineAdapter : RecyclerView.Adapter<CdvNewsHeadlieVH>(){

    private val listData = ArrayList<ArticlesItem>()

    fun addData(items: List<ArticlesItem>){
        listData.clear()
        listData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlieVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater, parent, false)
        return CdvNewsHeadlieVH(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: CdvNewsHeadlieVH, position: Int) {
        holder.bind(listData[position])
    }
}

class CdvNewsHeadlieVH(private val binding: CdvNewsHeadlineBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(article: ArticlesItem){
        binding.run {
            txtTitle.text = cropText(article.title?: "Tidak ada judul")
            txtSubtitle.text = article.publishedAt
            imgHeadline.apply {
                load(article.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = article.description
            }
            root.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.DETAIL_NEWS, article) // DETAIL_NEWS BERFUNGSI SEBAGAI VARIABLE YANG AKAN  DIKIRIMKAN KE DETAIL ACTIVITY
                }
                it.context.startActivity(intent)
            }
        }
    }
    private fun cropText(text: String): String {
        return text.take(50) + if (text.length > 50)"..." else ""
    }
}