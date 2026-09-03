package com.maclanzh.venezuelan.domain.usecase

import com.maclanzh.venezuelan.domain.model.Product
import com.maclanzh.venezuelan.domain.repository.ProductRepository

class GetProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product>{
        return repository.getProducts()
    }
}