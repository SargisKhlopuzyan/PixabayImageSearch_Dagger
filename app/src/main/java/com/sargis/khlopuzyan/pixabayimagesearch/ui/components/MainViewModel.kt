package com.sargis.khlopuzyan.pixabayimagesearch.ui.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.pixabayimagesearch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    val mainState: MutableState<MainState> = mutableStateOf(MainState())

    fun getImageList(q: String) {
        viewModelScope.launch {
            mainState.value = MainState(
                isLoading = true,
                error = ""
            )

            try {
                val result = mainRepository.getQueryItems(q)
                when (result) {
                    is Resource.Error -> {
                        mainState.value = MainState(
                            isLoading = false,
                            error = "Something went wrong"
                        )
                    }

                    is Resource.Success -> {
                        val result = result.data?.hits ?: emptyList()
                        mainState.value = MainState(
                            isLoading = false,
                            data = result,
                            error = ""
                        )
                    }
                }
            } catch (e: Exception) {
                mainState.value = MainState(
                    isLoading = false,
                    error = "Something went wrong"
                )
            }
        }
    }
}