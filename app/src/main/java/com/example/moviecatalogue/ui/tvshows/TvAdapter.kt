package com.example.moviecatalogue.ui.tvshows

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_tv.view.*

class TvAdapter: PagedListAdapter<RTvEntity, TvAdapter.TvViewHolder>(DIFF_CALLBACK) {

    private var listTv = ArrayList<RTvEntity>()

    fun setTv(tv: List<RTvEntity>?) {
        if (tv.isNullOrEmpty()) return
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
        @SuppressLint("SetTextI18n")
        fun bind(tv: RTvEntity) {
            var overview = ""
            overview = when {
                tv.overview == "" -> {
                    "-"
                }
                tv.overview.length < 50 -> {
                    tv.overview.substring(0,30) + "..."
                }
                tv.overview.length < 100 -> {
                    tv.overview.substring(0,50) + "..."
                }
                else -> {
                    tv.overview.substring(0,100) + "..."
                }
            }
            with(itemView) {
                tv_item_title.text = tv.title
                tv_item_years.text = tv.years
                tv_item_rating.text = tv.rating.toString()
                tv_item_overview.text = overview
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_DETAIL, tv.movieId)
                        putExtra(DetailActivity.EXTRA_TYPE, "tv")
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + tv.image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_default_glide)
                            .error(R.drawable.ic_default_glide))
                    .into(img_poster)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RTvEntity>() {
            override fun areItemsTheSame(oldItem: RTvEntity, newItem: RTvEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: RTvEntity, newItem: RTvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}