package com.example.sampleapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.common.Resource
import com.example.sampleapp.domain.model.Brochure
import com.example.sampleapp.domain.useCase.BrochuresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val brochuresUseCase: BrochuresUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BrochureState())
    val state: StateFlow<BrochureState> = _state
    var hasFilter = false
    private var brochures = listOf<Brochure>()

    init {
        getBrochures()
    }

    private fun getBrochures() {
        brochuresUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = BrochureState(brochures = result.data ?: emptyList())
                    brochures = result.data ?: emptyList()
                }
                is Resource.Error -> {
                    _state.value = BrochureState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = BrochureState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun enableFilter() {
        hasFilter = hasFilter.not()
        if (hasFilter) {
            _state.value = BrochureState(brochures = _state.value.brochures.filter { it.distance < 5.0 && it.distance != 0.0 })
        } else {
            _state.value = BrochureState(brochures = brochures)
        }
    }
}