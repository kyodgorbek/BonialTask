package com.example.sampleapp.di

import com.example.sampleapp.common.Constants.BASE_URL
import com.example.sampleapp.data.remote.BrochureService
import com.example.sampleapp.data.remote.dto.ContentType
import com.example.sampleapp.data.remote.dto.SuperContent
import com.example.sampleapp.data.repository.BrochureRepositoryImpl
import com.example.sampleapp.domain.repository.BrochureRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val moshi: MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory
                    .of(SuperContent::class.java, "contentType")
                    .withSubtype(SuperContent.Brochure::class.java, ContentType.brochure.name)
                    .withSubtype(SuperContent.BrochurePremium::class.java, ContentType.brochurePremium.name)
                    .withSubtype(SuperContent.CashbackOffersV2::class.java, ContentType.cashbackOffersV2.name)
                    .withSubtype(SuperContent.RateUs::class.java, ContentType.rateUs.name)
                    .withSubtype(SuperContent.SuperBannerCarousel::class.java, ContentType.superBannerCarousel.name)
                    .withSubtype(SuperContent.StoryCarousel::class.java, ContentType.storyCarousel.name)
                    .withSubtype(SuperContent.BlogCarousel::class.java, ContentType.blogCarousel.name)
                    .withSubtype(SuperContent.Referral::class.java, ContentType.referral.name)
                    .withDefaultValue(SuperContent.Other(ContentType.other.name))
            )
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient()
                    .newBuilder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
            )
            .addConverterFactory(moshi)
            .build()
    }

    @Singleton
    @Provides
    fun provideBrochureService(retrofit: Retrofit): BrochureService {
        return retrofit.create(BrochureService::class.java)
    }

    @Provides
    @Singleton
    fun provideBrochureRepository(api: BrochureService): BrochureRepository {
        return BrochureRepositoryImpl(api)
    }
}