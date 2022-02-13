package com.example.sampleapp.data.remote.dto.contents

data class SuperBannerCarouselContent(
    val content: Content?
) {
    data class Content(
        val id: String?,
        val publisherId: String?,
        val publishedFrom: String?,
        val publishedUntil: String?,
        val clickUrl: String?,
        val imageUrl: String?
    )
}
