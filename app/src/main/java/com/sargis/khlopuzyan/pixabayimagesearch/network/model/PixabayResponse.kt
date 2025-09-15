package com.sargis.khlopuzyan.pixabayimagesearch.network.model

data class PixabayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)