package com.example.sampleapp.domain.useCase

import com.example.sampleapp.common.Resource
import com.example.sampleapp.domain.model.Brochure
import com.example.sampleapp.domain.repository.BrochureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BrochuresUseCase @Inject constructor(
    private val repository: BrochureRepository
) {
    operator fun invoke(): Flow<Resource<List<Brochure>>> = repository.getBrochures()
}