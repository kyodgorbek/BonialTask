package com.example.sampleapp.data.remote.dto.contents

data class BrochureContent(
    val id: Long?,
    val title: String?,
    val validFrom: String?,
    val validUntil: String?,
    val publishedFrom: String?,
    val publishedUntil: String?,
    val type: String?,
    val pageCount: Int?,
    val publisher: Publisher?,
    val retailer: Retailer?,
    val brochureImage: String?,
    val badges: List<Any>?,
    val isEcommerce: Boolean?,
    val distance: Double?,
    val hideValidityDate: Boolean?
) {
    data class Publisher(
        val id: Long?,
        val name: String?,
        val type: String?
    )

    data class Retailer(
        val id: Long?,
        val name: String?,
    )
}



