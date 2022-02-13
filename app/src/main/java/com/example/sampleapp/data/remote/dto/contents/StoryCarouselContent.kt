package com.example.sampleapp.data.remote.dto.contents

data class StoryCarouselContent(
    val title: String?,
    val publisherId: Int?,
    val publisherImage: String,
    val items: List<Item>
) {
    data class Item(val content: Content)

    data class Content(
        val id: String?,
        val publisherId: Int?,
        val publishedFrom: String?,
        val publishedUntil: String?,
        val linkOut: LinkOut?,
        val video: Video?,
        val type: String?
    )

    data class LinkOut(
        val label: String?,
        val mobileUrl: String?,
        val webUrl: String?,
        val bgColor: String?,
        val textColor: String?
    )

    data class Video(val sizes: List<Size>)

    data class Size(
        val dimensions: Dimensions?,
        val url: String?
    )

    data class Dimensions(
        val height: Int,
        val width: Int?
    )
}
