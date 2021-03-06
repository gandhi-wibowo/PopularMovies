package com.dizcoding.popularmovies.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dizcoding.core.BuildConfig.IMDB_IMG_URL
import com.dizcoding.core.databinding.ItemFilmBinding
import com.dizcoding.core.domain.model.Movie
import com.dizcoding.core.utils.SinggleClickEvent
import com.dizcoding.core.utils.intentTo
import com.dizcoding.popularmovies.R
import com.dizcoding.popularmovies.databinding.FragmentMovieBinding
import com.dizcoding.popularmovies.detail.DetailActivity
import com.utsman.recycling.setupAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModel()
    private lateinit var viewBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            viewModel.getMovie().observe(viewLifecycleOwner, { movies ->
                viewBinding.rvMovies.setupAdapter<Movie>(R.layout.item_film) { _, context, _ ->
                    bind { itemView, _, item ->
                        val binding = ItemFilmBinding.bind(itemView)
                        Glide
                            .with(context)
                            .load(IMDB_IMG_URL + item?.poster_path)
                            .fitCenter()
                            .into(binding.ivPoster)
                        binding.tvTitle.text = item?.title
                        binding.tvDescription.text = item?.overview
                        binding.cvFilm.setOnClickListener(object : SinggleClickEvent {
                            override fun onClick(v: View?) {
                                super.onClick(v)
                                requireActivity().intentTo(DetailActivity::class.java, bundleOf("movieId" to item?.id))
                            }
                        })
                    }
                    submitList(movies)
                }
            })
        }
    }

}