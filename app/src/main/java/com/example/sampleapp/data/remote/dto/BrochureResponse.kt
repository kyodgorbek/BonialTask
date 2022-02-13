package com.example.sampleapp.data.remote.dto

import com.squareup.moshi.Json

data class BrochureResponse(
    @Json(name = "_embedded")
    val embedded: Embedded,
    @Json(name = "_links")
    val links: Links,
    val page: Page?
) {
    data class Embedded(val contents: List<SuperContent>?)

    data class Links(val self: Self)

    data class Self(val href: String?)

    data class Page(
        val size: Int?,
        val totalElements: Int?,
        val totalPages: Int?,
        val number: Int?
    )
}

