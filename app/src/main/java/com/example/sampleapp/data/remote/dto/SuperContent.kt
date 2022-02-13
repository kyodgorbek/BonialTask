package com.example.sampleapp.data.remote.dto

import com.example.sampleapp.data.remote.dto.contents.*
import com.example.sampleapp.domain.model.BrochureConverter
import com.squareup.moshi.Json

sealed class SuperContent(@Json(name = "contentType") val type: ContentType) {

    data class Brochure(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String,
        val content: BrochureContent?
    ) : SuperContent(ContentType.brochure), BrochureConverter {
        override fun convert(): com.example.sampleapp.domain.model.Brochure {
            return com.example.sampleapp.domain.model.Brochure(
                image = this.content?.brochureImage,
                retailerName = this.content?.retailer?.name ?: "Name not found!",
                distance = this.content?.distance ?: 0.0,
                isPremium = false
            )
        }
    }

    data class BrochurePremium(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String,
        val content: BrochureContent?
    ) : SuperContent(ContentType.brochurePremium), BrochureConverter {
        override fun convert(): com.example.sampleapp.domain.model.Brochure {
            return com.example.sampleapp.domain.model.Brochure(
                image = this.content?.brochureImage,
                retailerName = this.content?.retailer?.name ?: "Name not found!",
                distance = this.content?.distance ?: 0.0,
                isPremium = true
            )
        }
    }

    data class Other(
        val contentType: String?,
    ) : SuperContent(ContentType.other)

    data class CashbackOffersV2(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String,
        val content: List<CashbackOffersContent>?
    ) : SuperContent(ContentType.cashbackOffersV2)

    data class RateUs(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String,
        val content: Any?
    ) : SuperContent(ContentType.rateUs)

    data class SuperBannerCarousel(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String,
        val content: List<SuperBannerCarouselContent>?
    ) : SuperContent(ContentType.superBannerCarousel)

    data class StoryCarousel(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String,
        val content: List<StoryCarouselContent>?
    ) : SuperContent(ContentType.storyCarousel)

    data class BlogCarousel(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String,
        val content: List<BlogCarouselContent>?
    ) : SuperContent(ContentType.blogCarousel)

    data class Referral(
        val placement: String?,
        val adFormat: String?,
        val contentFormatSource: String?,
        val contentType: String,
        val content: ReferralContent?
    ) : SuperContent(ContentType.referral)

}

