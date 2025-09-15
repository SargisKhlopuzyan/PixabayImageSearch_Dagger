package com.sargis.khlopuzyan.pixabayimagesearch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.sargis.khlopuzyan.pixabayimagesearch.network.model.Hit

@Preview
@Composable
fun MainContent( modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {

    val query: MutableState<String> = remember {
        mutableStateOf("")
    }

    Surface(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = query.value, onValueChange = {
                    query.value = it
                    viewModel.getImageList(query.value)
                },
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            )

            if (viewModel.mainState.value.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else if (viewModel.mainState.value.error.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = viewModel.mainState.value.error
                    )
                }
            } else if (viewModel.mainState.value.data.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                ) {
                    viewModel.mainState.value.data?.let {
                        items(it) {
                            MainContentItem(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainContentItem(hit: Hit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = hit.largeImageURL),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
        )
    }
}