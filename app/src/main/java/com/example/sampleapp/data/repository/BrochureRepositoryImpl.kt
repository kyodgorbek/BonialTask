package com.example.sampleapp.data.repository

import com.example.sampleapp.common.Resource
import com.example.sampleapp.data.remote.BrochureService
import com.example.sampleapp.domain.model.Brochure
import com.example.sampleapp.domain.model.BrochureConverter
import com.example.sampleapp.domain.repository.BrochureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class BrochureRepositoryImpl constructor(
    private val service: BrochureService
) : BrochureRepository {
    override fun getBrochures(): Flow<Resource<List<Brochure>>> = flow {
        try {
            emit(Resource.Loading())
            val response = service.getBrochures()
            if (response.isSuccessful) {

                // Filtering Brochures
                val filtered = response.body()?.embedded?.contents?.filterIsInstance<BrochureConverter>()?.map {
                    it.convert()
                }

                if (filtered.isNullOrEmpty()) {
                    emit(Resource.Error("Brochures not found"))
                } else {
                    emit(Resource.Success(filtered))
                }
            } else {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}