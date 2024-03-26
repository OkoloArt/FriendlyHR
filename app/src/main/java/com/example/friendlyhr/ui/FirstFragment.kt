package com.example.friendlyhr.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.friendlyhr.databinding.FragmentFirstBinding
import com.example.friendlyhr.epoxy.PositionController
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment()
{

    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle? ,
    ): View?
    {

        _binding = FragmentFirstBinding.inflate(inflater , container , false)
        return binding.root

    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?)
    {
        super.onViewCreated(view , savedInstanceState)

        val controller = PositionController {}
        binding.epoxyRecyclerView.setController(controller)

        val viewModel: FirstViewModel by viewModels()

        lifecycleScope.launchWhenStarted {
                viewModel.positionUiState.collect { uiState ->
                    when (uiState) {
                        is PositionUiState.Success -> {
                            // Handle success state
                            val positions = uiState.positions
                            controller.setData(positions)
                            binding.epoxyRecyclerView.setController(controller)
                            binding.loadingBar.visibility = View.INVISIBLE
                            binding.epoxyRecyclerView.visibility = View.VISIBLE

                        }
                        is PositionUiState.Loading -> {
                            // Handle loading state
                            binding.epoxyRecyclerView.visibility = View.INVISIBLE
                            binding.loadingBar.visibility = View.VISIBLE
                        }
                        is PositionUiState.Error -> {
                            // Handle error state
                            val error = uiState.error
                            // Handle the error, for example, show an error message
                        }
                    }
                }
            }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}