package com.example.moviecatalogue.ui.menu.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.SortUtils
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_fav_movies.*
import kotlinx.android.synthetic.main.fragment_fav_tv.*

class FavTvFragment : Fragment() {

    private lateinit var viewModel: FavTvViewModel
    private lateinit var tvAdapter: FavTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(rv_tv_favorite)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavTvViewModel::class.java]

            tvAdapter = FavTvAdapter()

            progress_bar_tv_favorite.visibility = View.VISIBLE
            viewModel.getTv(SortUtils.NEWEST).observe(requireActivity(), { tv ->
                progress_bar_tv_favorite.visibility = View.GONE
                tvAdapter.submitList(tv)
            })

            with(rv_tv_favorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvAdapter
            }

            rd_sort_tv.setOnCheckedChangeListener(
                    RadioGroup.OnCheckedChangeListener { group, id ->
                        when (id) {
                            R.id.radio_newest -> {
                                viewModel.getTv(SortUtils.NEWEST).observe(viewLifecycleOwner, { movies ->
                                    tvAdapter.submitList(movies)
                                })
                            }
                            R.id.radio_oldest -> {
                                viewModel.getTv(SortUtils.OLDEST).observe(viewLifecycleOwner, { movies ->
                                    tvAdapter.submitList(movies)
                                })
                            }
                            R.id.radio_random -> {
                                viewModel.getTv(SortUtils.RANDOM).observe(viewLifecycleOwner, { movies ->
                                    tvAdapter.submitList(movies)
                                })
                            }
                        }
                    }
            )
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = tvAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}