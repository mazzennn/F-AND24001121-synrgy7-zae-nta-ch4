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
import com.example.chapter_4.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var mySharedPreferece: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mySharedPreferece = SharedPreference(requireContext())

        binding.btnRegister.setOnClickListener(){
            val username = binding.usernameTextField.text.toString()
            val email = binding.emailTextField.text.toString()
            val password = binding.passwordTextField.text.toString()
            val confirmPassword = binding.confirmPasswordTextField.text.toString()

            if (password != confirmPassword) {
                // Jika password dan konfirmasi password tidak cocok, tampilkan pesan kesalahan
                binding.confirmPasswordTextField.error = "Konfirmasi password tidak cocok"
//                return@setOnClickListener
            }else{
                mySharedPreferece.saveUserData(username, email, password)
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)


            }
        }
    }

}