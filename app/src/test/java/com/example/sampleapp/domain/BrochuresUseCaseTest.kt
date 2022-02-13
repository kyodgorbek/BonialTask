package com.example.sampleapp.domain

import com.example.sampleapp.data.MockRepository
import com.example.sampleapp.domain.useCase.BrochuresUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BrochuresUseCaseTest {

    private lateinit var repository: MockRepository

    lateinit var useCase: BrochuresUseCase

    @Before
    fun setUp() {
        repository = MockRepository()
        useCase = BrochuresUseCase(repository)
    }

    @Test
    fun `List is not empty`() = runBlocking {
        useCase.invoke().collect {
            assertTrue(it.data?.isNullOrEmpty() == false)
        }
    }

    @Test
    fun `Has premium brochures`() = runBlocking {
        useCase.invoke().collect {
            assertTrue(it.data?.find { it.isPremium } != null)
        }
    }

    @Test
    fun `Brochures count`() = runBlocking {
        useCase.invoke().collect {
            assertTrue(it.data?.size == repository.brochures.size)
        }
    }

}