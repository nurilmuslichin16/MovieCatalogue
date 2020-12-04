package com.example.moviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_tv.view.*

class TvAdapter: RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    private var listTv = ArrayList<MovieEntity>()

    fun setTv(tv: List<MovieEntity>?) {
        if (tv == null) return
        listTv.clear()
        listTv.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapter.TvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvAdapter.TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int = listTv.size

    class TvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(tv: MovieEntity) {
            with(itemView) {
                tv_item_title.text = tv.title
                tv_item_years.text = tv.years
                tv_item_rating.text = tv.rating
                tv_item_category.text = tv.category
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_DETAIL, tv.movieId)
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(tv.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                            .error(R.drawable.ic_default_glide))
                    .into(img_poster)
            }
        }
    }

}