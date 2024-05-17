package com.example.challenge_ch5.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge_ch5.adapter.AdapterMovie
import com.example.challenge_ch5.databinding.FragmentListBinding
import com.example.challenge_ch5.helper.Status
import com.example.challenge_ch5.model.MovieDetail
import com.example.challenge_ch5.viewmodel.MovieViewModel
import com.example.challenge_ch5.viewmodel.UserViewModel
import com.example.challenge_ch5.viewmodel.ViewModelFactory

class ListFragment : Fragment(), AdapterMovie.OnNoteItemClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterMovie: AdapterMovie

    private val movieViewModel: MovieViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    private val userViewModel : UserViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()

        movieViewModel.fetchMoviePlayingNow().observe(viewLifecycleOwner){ resources->
            when (resources.status) {
                Status.SUCESSS -> {

                    val data = resources.data
                    val movieResult = data?.results // change movieResponse to MovieResult
                    adapterMovie = AdapterMovie(movieResult,this )

                    binding.rvMoviePlayingNow.adapter = adapterMovie
                    binding.rvMoviePlayingNow.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                }

                Status.ERROR -> {
                    val response = resources.message
                    Toast.makeText(requireContext(), "Failed to Show Movie", Toast.LENGTH_SHORT).show()
                    Log.e("SimpleRetrofit", response.toString())
                }

                Status.LOADING -> {}
            }
        }

        movieViewModel.fetchMovieTopRated().observe(viewLifecycleOwner){ resources->
            when (resources.status) {
                Status.SUCESSS -> {

                    val data = resources.data
                    val movieResult = data?.results // change movieResponse to MovieResult
                    adapterMovie = AdapterMovie(movieResult,this )

                    binding.rvMovieTopRated.adapter = adapterMovie
                    binding.rvMovieTopRated.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                }

                Status.ERROR -> {
                    val response = resources.message
                    Toast.makeText(requireContext(), "Failed to Show Movie", Toast.LENGTH_SHORT).show()
                    Log.e("SimpleRetrofit", response.toString())
                }

                Status.LOADING -> {}
            }
        }




    }

    private fun setToolbar(){
        userViewModel.apply {
            getUsername().observe(viewLifecycleOwner){
                binding.toolbar.tvWelcome.setText("Welcome, ${it}" )
            }
        }

        binding.toolbar.btnLogout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUserFragment()
            findNavController().navigate(action)
        }

        binding.toolbar.tvPageTitle.text = "Home"
    }


    override fun onItemClick(movieDetail: MovieDetail) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(movieDetail.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
