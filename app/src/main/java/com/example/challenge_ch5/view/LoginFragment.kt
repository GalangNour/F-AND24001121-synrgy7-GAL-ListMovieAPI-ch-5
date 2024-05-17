package com.example.challenge_ch5.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challenge_ch5.R
import com.example.challenge_ch5.databinding.FragmentLoginBinding
import com.example.challenge_ch5.viewmodel.UserViewModel
import com.example.challenge_ch5.viewmodel.ViewModelFactory


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        binding.buttonLogin.setOnClickListener {
            val inputUsername = binding.editTextUsername.editText?.text.toString()
            val inputPassword = binding.editTextPassword.editText?.text.toString()
            userViewModel.apply {
                getUsername().observe(viewLifecycleOwner){ userNameRegistered ->
                    getPassword().observe(viewLifecycleOwner){ passwordRegistered ->
                        if (inputUsername == userNameRegistered && inputPassword == passwordRegistered) {
                            Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                            setUserLoggedIn(true)
                            findNavController().navigate(R.id.action_loginFragment_to_listFragment)
                        } else {
                            Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.btnRegisterHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun setObserver(){
        userViewModel.apply {
            isUserLoggedIn().observe(viewLifecycleOwner) { isLoggedIn ->
                if (isLoggedIn) {
                    findNavController().navigate(R.id.action_loginFragment_to_listFragment)
                    Toast.makeText(requireContext(), "You Already Logged in", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}