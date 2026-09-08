package com.maclanzh.venezuelan.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maclanzh.venezuelan.R
import com.maclanzh.venezuelan.presentation.screens.viewmodel.HomeScreen
import com.maclanzh.venezuelan.presentation.viewmodel.ProductUiState
import com.maclanzh.venezuelan.presentation.viewmodel.ProductViewmodel

@Composable
fun ProductScreen(
    viewmodel: ProductViewmodel,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewmodel.loadProducts()
    }

    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is ProductUiState.Loading -> {
                CircularProgressIndicator()
            }

            is ProductUiState.Success -> {
                HomeScreen(products = state.products)
            }

            is ProductUiState.Error -> {
                Text(
                    text = stringResource(R.string.error, state.message),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}