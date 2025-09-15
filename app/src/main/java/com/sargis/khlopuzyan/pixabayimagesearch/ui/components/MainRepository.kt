package com.sargis.khlopuzyan.pixabayimagesearch.ui.components

import com.sargis.khlopuzyan.pixabayimagesearch.network.ApiService
import com.sargis.khlopuzyan.pixabayimagesearch.network.model.PixabayResponse
import com.sargis.khlopuzyan.pixabayimagesearch.util.Constant
import com.sargis.khlopuzyan.pixabayimagesearch.util.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getQueryItems(q: String): Resource<PixabayResponse> {
        try {
            val result = apiService.getQueryImages(
                query = q,
                apiKey = Constant.API_KEY,
                imageType = "photo"
            )
            return Resource.Success(data = result)
        } catch (e: Exception) {
            return Resource.Error(message = e.message)
        }
    }
}