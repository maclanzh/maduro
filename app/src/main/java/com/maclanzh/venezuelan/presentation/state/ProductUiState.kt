package com.maclanzh.venezuelan.presentation.state

import com.maclanzh.venezuelan.domain.model.Product


sealed interface ProductUiState {
    object Loading : ProductUiState
    data class Success(val products : List<Product>) : ProductUiState
    data class Error(val message : String) : ProductUiState
}