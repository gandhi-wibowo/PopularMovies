package com.dizcoding.popularmovies.vaforite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dizcoding.popularmovies.BuildConfig
import com.dizcoding.popularmovies.R
import com.dizcoding.popularmovies.core.domain.model.Movie
import com.dizcoding.popularmovies.core.ui.ViewModelFactory
import com.dizcoding.popularmovies.core.utils.SinggleClickEvent
import com.dizcoding.popularmovies.core.utils.intentTo
import com.dizcoding.popularmovies.databinding.FragmentVaforiteBinding
import com.dizcoding.popularmovies.detail.DetailActivity
import com.utsman.recycling.setupAdapter
import kotlinx.android.synthetic.main.item_film.view.*
import org.koin.android.viewmodel.ext.android.viewModel

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

            viewModel.getLocalMovie().observe(viewLifecycleOwner, { movies ->
                viewBinding.rvMovies.setupAdapter<Movie>(R.layout.item_film) { _, context, _ ->
                    bind { itemView, _, item ->
                        Glide
                            .with(context)
                            .load(BuildConfig.IMDB_IMG_URL + item?.poster_path)
                            .fitCenter()
                            .into(itemView.ivPoster)
                        itemView.tvTitle.text = item?.title
                        itemView.tvDescription.text = item?.overview
                        itemView.cvFilm.setOnClickListener(object : SinggleClickEvent {
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