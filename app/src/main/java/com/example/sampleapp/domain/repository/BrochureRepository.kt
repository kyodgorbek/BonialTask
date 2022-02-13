package com.example.sampleapp.domain.repository

import com.example.sampleapp.common.Resource
import com.example.sampleapp.domain.model.Brochure
import kotlinx.coroutines.flow.Flow

interface BrochureRepository {
    fun getBrochures(): Flow<Resource<List<Brochure>>>
}