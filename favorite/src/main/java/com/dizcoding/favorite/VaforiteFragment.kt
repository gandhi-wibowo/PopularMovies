package com.dizcoding.favorite

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
import com.dizcoding.core.R
import com.dizcoding.favorite.databinding.FragmentVaforiteBinding
import com.dizcoding.popularmovies.detail.DetailActivity
import com.utsman.recycling.setupAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class VaforiteFragment : Fragment() {
    private val viewModel: VaforiteViewModel by viewModel()
    private lateinit var viewBinding: FragmentVaforiteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentVaforiteBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (activity != null) {
            loadKoinModules(favoriteModule)
            viewModel.getLocalMovie().observe(viewLifecycleOwner, { movies ->
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