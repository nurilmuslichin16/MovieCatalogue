package com.example.moviecatalogue.ui.menu.movies

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

class FavMoviesFragment : Fragment() {

    private lateinit var viewModel: FavMovieViewModel
    private lateinit var movieAdapter: FavMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(rv_movies_favorite)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]

            movieAdapter = FavMovieAdapter()

            progress_bar_movies_favorite.visibility = View.VISIBLE
            viewModel.getMovies(SortUtils.NEWEST).observe(viewLifecycleOwner, { movies ->
                progress_bar_movies_favorite.visibility = View.GONE
                movieAdapter.submitList(movies)
            })

            with(rv_movies_favorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = movieAdapter
            }

            rd_sort_movie.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener {group, id ->
                    when (id) {
                        R.id.radio_newest -> {
                            viewModel.getMovies(SortUtils.NEWEST).observe(viewLifecycleOwner, { movies ->
                                movieAdapter.submitList(movies)
                            })
                        }
                        R.id.radio_oldest -> {
                            viewModel.getMovies(SortUtils.OLDEST).observe(viewLifecycleOwner, { movies ->
                                movieAdapter.submitList(movies)
                            })
                        }
                        R.id.radio_random -> {
                            viewModel.getMovies(SortUtils.RANDOM).observe(viewLifecycleOwner, { movies ->
                                movieAdapter.submitList(movies)
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
                val courseEntity = movieAdapter.getSwipedData(swipedPosition)
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