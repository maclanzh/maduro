package com.maclanzh.venezuelan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maclanzh.venezuelan.domain.model.Product
import com.maclanzh.venezuelan.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewmodel(
    private val getProductUsecase: GetProductUseCase
) : ViewModel() {

    private val _uistate = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState : StateFlow<ProductUiState> = _uistate.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts(){
        viewModelScope.launch {
            _uistate.value = ProductUiState.Loading
            try {
                val products = getProductUsecase()
                _uistate.value = ProductUiState.Success(products)
            } catch (e: Exception){
                _uistate.value = ProductUiState.Error(e.message ?: "خطا ناشناخته")
            }
        }
    }
}

sealed interface ProductUiState {
    object Loading : ProductUiState
    data class Success(val products : List<Product>) : ProductUiState
    data class Error(val message : String) : ProductUiState
}