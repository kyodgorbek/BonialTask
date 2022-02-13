package com.example.sampleapp.data

import com.example.sampleapp.common.Resource
import com.example.sampleapp.domain.model.Brochure
import com.example.sampleapp.domain.repository.BrochureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockRepository : BrochureRepository {
    val brochures = listOf(
        Brochure("", "John", false, 3.0),
        Brochure("", "Smith", false, 7.0),
        Brochure("", "Joan", true, 9.0),
        Brochure("", "Ben", false, 2.0),
        Brochure("", "Alex", false, 1.0),
    )
    override fun getBrochures(): Flow<Resource<List<Brochure>>> {
        return flow { emit(Resource.Success(brochures)) }
    }
}