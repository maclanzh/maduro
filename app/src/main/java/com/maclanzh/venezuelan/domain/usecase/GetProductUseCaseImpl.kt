package com.maclanzh.venezuelan.domain.usecase

import com.maclanzh.venezuelan.domain.model.Product
import com.maclanzh.venezuelan.domain.repository.ProductRepository

class GetProductUseCaseImpl(
    private val repository: ProductRepository
) : GetProductUseCase {
    override suspend operator fun invoke(): List<Product>{
        return repository.getProducts()
    }
}