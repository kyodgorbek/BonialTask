package com.example.sampleapp.data.remote

import com.example.sampleapp.data.remote.dto.BrochureResponse
import retrofit2.Response
import retrofit2.http.GET

interface BrochureService {
    @GET("stories-test/shelf.json")
    suspend fun getBrochures(): Response<BrochureResponse>
}