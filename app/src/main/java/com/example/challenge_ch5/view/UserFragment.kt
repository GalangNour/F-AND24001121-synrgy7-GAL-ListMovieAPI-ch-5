package com.example.challenge_ch5.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.challenge_ch5.R
import com.example.challenge_ch5.databinding.FragmentUserBinding
import com.example.challenge_ch5.viewmodel.UserViewModel
import com.example.challenge_ch5.viewmodel.ViewModelFactory


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val userViewModel : UserViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        binding.btnUpdate.setOnClickListener {

            val username = binding.editTextUsername.editText?.text.toString()
            val password = binding.editTextPassword.editText?.text.toString()
            val konfirmPassword = binding.editTextPasswordConfirm.editText?.text.toString()
            val email = binding.editTextEmail.editText?.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                if(password == konfirmPassword){
                    userViewModel.registerUser(username, password, email)
                    findNavController().navigateUp()
                }else{
                    Toast.makeText(requireContext(), "Password not match", Toast.LENGTH_SHORT).show() }
            }else{
                Toast.makeText(requireContext(), "Please fill the form first", Toast.LENGTH_SHORT).show() }
        }

        binding.btnLogout.setOnClickListener {
            userViewModel.setUserLoggedIn(false)
        }


    }


    private fun setObserver(){

        binding.tvPageTitle.text = "User Profile"

        userViewModel.apply {
            getUsername().observe(viewLifecycleOwner){
                binding.editTextUsername.editText?.setText(it.toString())
            }
            getEmail().observe(viewLifecycleOwner){
                binding.editTextEmail.editText?.setText(it.toString())
            }

            isUserLoggedIn().observe(viewLifecycleOwner) { isLoggedIn ->
                if (!isLoggedIn) {
                    findNavController().navigate(R.id.action_userFragment_to_loginFragment)
                }
            }
        }
    }

}