package com.example.project.presentation.Auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.databinding.FragmentRegisterBinding
import com.example.project.domain.AuthState
import com.example.project.presentation.Auth.ViewModels.RegisterViewModel

import com.example.project.presentation.MainApplication
import com.example.project.presentation.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val args by navArgs<RegisterFragmentArgs>()

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.linearLayoutProgress.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSignUp.setOnClickListener {
            val email = binding.edtEmailID.text.toString()
            val pass = binding.edtPassword.text.toString()
            val name = binding.edtName.text.toString()
            val surName = binding.edtSurname.text.toString()
            viewModel.register(email,pass, name, surName, args.type)
            lifecycleScope.launch{
                viewModel.stateFlow.collect{
                    when(it) {
                        is AuthState.Loading ->{
                            withContext(Dispatchers.Main){
                                binding.linearLayoutProgress.visibility = View.VISIBLE
                                view.hideKeyboard()
                            }
                        }
                        is AuthState.Error ->{
                            withContext(Dispatchers.Main){
                                Toast.makeText(requireActivity(), "Ошибка", Toast.LENGTH_SHORT).show()
                                binding.linearLayoutProgress.visibility = View.GONE
                            }
                        }
                        is AuthState.Success ->{
                            withContext(Dispatchers.Main){
                                Toast.makeText(requireActivity(), "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show()
                            }
                            findNavController().navigateUp()
                        }

                    }
                }
            }
        }


    }

    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}