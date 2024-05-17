package com.example.challenge_ch5.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.challenge_ch5.databinding.FragmentDetailBinding
import com.example.challenge_ch5.helper.Status
import com.example.challenge_ch5.viewmodel.MovieViewModel
import com.example.challenge_ch5.viewmodel.ViewModelFactory

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var movieId: Int = 0

    private val viewModel: MovieViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
            movieId = args.movieId
        }

        binding.tvPageTitle.text = "Detail"

        viewModel.getDetailMovie(movieId).observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCESSS -> {
                    val detailedMovie = resource.data
                    if (detailedMovie != null) {
                        binding.tvTitle.text = detailedMovie.title
                        binding.tvOverview.text = detailedMovie.overview
                        Glide.with(binding.imgMovie)
                            .load("https://image.tmdb.org/t/p/w500${detailedMovie.posterPath}")
                            .into(binding.imgMovie)

                    }
                }
                Status.ERROR -> {
                    val errorMessage = resource.message ?: "Error fetching data"
                    Log.e("DetailFragment", errorMessage)
                }
                Status.LOADING -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

