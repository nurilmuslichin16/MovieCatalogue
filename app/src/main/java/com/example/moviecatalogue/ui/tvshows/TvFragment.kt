package com.example.moviecatalogue.ui.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val academyAdapter = TvAdapter()

            progress_bar_tv.visibility = View.VISIBLE
            viewModel.getTv().observe(requireActivity(), {tv ->
                progress_bar_tv.visibility = View.GONE
                academyAdapter.setTv(tv)
                academyAdapter.notifyDataSetChanged()
            })

            with(rv_tv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = academyAdapter
            }
        }
    }

    companion object {

    }
}