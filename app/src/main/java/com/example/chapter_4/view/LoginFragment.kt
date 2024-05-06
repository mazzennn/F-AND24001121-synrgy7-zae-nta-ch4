package com.example.chapter_4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.chapter_4.Model.SharedPreference
import com.example.chapter_4.R
import com.example.chapter_4.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var mySharedPreferece: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySharedPreferece = SharedPreference(requireContext())

        binding.btnLogin.setOnClickListener(){
            val username = binding.usernameTextField.text.toString()
            val password = binding.passwordTextField.text.toString()

            if (mySharedPreferece.isValidLogin(username, password)) {
                findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
            } else {
                // Jika login gagal, tampilkan pesan kesalahan
                binding.passwordTextField.error = "Username atau password salah"
            }
        }
        binding.signUpText.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}