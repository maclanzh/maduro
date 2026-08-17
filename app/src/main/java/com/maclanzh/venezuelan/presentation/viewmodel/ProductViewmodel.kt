package com.maclanzh.venezuelan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maclanzh.venezuelan.domain.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewmodel(
    private val repository: ProductRepository
) : ViewModel() {

    fun loadProducts(){
        viewModelScope.launch {
            val products = repository.getProducts()
        }
    }
}