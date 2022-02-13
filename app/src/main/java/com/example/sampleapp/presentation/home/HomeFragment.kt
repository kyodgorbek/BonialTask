package com.example.sampleapp.presentation.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FragmentHomeBinding
import com.example.sampleapp.presentation.home.adapter.BrochureAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val brochureAdapter = BrochureAdapter()
    private val spanCount by lazy {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            3
        } else {
            2
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        lifecycleScope.launchWhenResumed {
            homeViewModel.state.onEach(this@HomeFragment::renderState).launchIn(this)
        }
    }

    private fun initViews() {
        binding.rvBrochures.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), spanCount).apply {
                spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (brochureAdapter.isPremium(position)) spanCount else 1
                    }
                }
            }
            adapter = brochureAdapter
        }

        binding.fabFilter.setOnClickListener {
            homeViewModel.enableFilter()
        }
    }

    private fun renderState(state: BrochureState) {
        binding.cpi.isVisible = state.isLoading

        if (state.error.isNotEmpty()) {
            Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
        } else if (state.brochures.isNotEmpty()) {
            binding.fabFilter.apply {
                visibility = View.VISIBLE
                setImageResource(if (homeViewModel.hasFilter) R.drawable.ic_baseline_clear_24 else R.drawable.ic_baseline_filter_alt_24)
            }
            binding.rvBrochures.visibility = View.VISIBLE
            brochureAdapter.setItems(state.brochures)
        }
    }
}