package com.example.challenge_ch5.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.challenge_ch5.databinding.FragmentRegisterBinding
import com.example.challenge_ch5.viewmodel.UserViewModel
import com.example.challenge_ch5.viewmodel.ViewModelFactory


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val userViewModel : UserViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val username = binding.editTextUsername.editText?.text.toString()
            val password = binding.editTextPassword.editText?.text.toString()
            val email = binding.editTextEmail.editText?.text.toString()

            userViewModel.registerUser(username, password, email)
            findNavController().navigateUp()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}