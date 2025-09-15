package com.sargis.khlopuzyan.pixabayimagesearch.ui.components

import com.sargis.khlopuzyan.pixabayimagesearch.network.model.Hit

data class MainState(
    val isLoading: Boolean = false,
    val data: List<Hit> = emptyList(),
    val error: String = "",
)