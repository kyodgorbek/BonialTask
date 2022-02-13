package com.example.sampleapp.presentation.home

import com.example.sampleapp.domain.model.Brochure

data class BrochureState(
    val isLoading: Boolean = false,
    val brochures: List<Brochure> = emptyList(),
    val error: String = ""
)
