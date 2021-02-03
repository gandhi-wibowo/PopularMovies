package com.dizcoding.popularmovies.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dizcoding.core.BuildConfig.IMDB_IMG_URL
import com.dizcoding.core.domain.model.Movie
import com.dizcoding.core.utils.SinggleClickEvent
import com.dizcoding.popularmovies.R
import com.dizcoding.popularmovies.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), SinggleClickEvent {
    private  val  viewModel: DetailViewModel by viewModel()

    private lateinit var binding: ActivityDetailBinding
    private var movieId: Int = 0
    private var movieDetailDatas: List<Movie> = listOf()
    private var movieDetailData: Movie? = null
    private var thisMovieVaforite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra("movieId", 0)

        viewModel.getDetail(movieId).observe(this, {
            if (movieDetailDatas.isEmpty()) {
                it.data?.let { movieList ->
                    movieDetailDatas = movieList
                    setView(movieDetailDatas)
                }
            } else setView(movieDetailDatas)
        })
        binding.cvBookmark.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        if (v == binding.cvBookmark) {
            if (thisMovieVaforite && movieId > 0) {
                viewModel.deleteFavorite(movieId)
                binding.vBookmark.background =
                    resources.getDrawable(R.drawable.vector_bookmark_border)
                thisMovieVaforite = false
                movieDetailData?.isFavorit = false
            } else {
                movieDetailData?.let {
                    if (it.id!! > 0) {
                        it.isFavorit = true
                        viewModel.insertFavorite(it)
                        binding.vBookmark.background =
                            resources.getDrawable(R.drawable.vector_bookmarked)
                        thisMovieVaforite = true
                    }
                    movieDetailData?.isFavorit = true
                }
            }
        }
    }

    private fun setView(movies: List<Movie>) {
        if (movies.isNotEmpty()) {
            val movie = movies[0]
            movieDetailData = movie
            binding.tvLang.text = StringBuilder("Lang : ${movie.original_language}")
            binding.tvDate.text = StringBuilder("Release Date : ${movie.release_date}")
            binding.tvRate.text = StringBuilder("Rate : ${movie.vote_average}")
            binding.tvTitleFilm.text = movie.title
            binding.tvDescription.text = movie.overview
            Glide
                .with(this)
                .load(IMDB_IMG_URL + movie.backdrop_path)
                .fitCenter()
                .into(binding.ivPoster)
            movie.isFavorit?.let { isVaforite ->
                thisMovieVaforite = isVaforite
                if (isVaforite) binding.vBookmark.background =
                    resources.getDrawable(R.drawable.vector_bookmarked)
                else binding.vBookmark.background =
                    resources.getDrawable(R.drawable.vector_bookmark_border)
            }
        }

    }
}