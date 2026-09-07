package com.maclanzh.venezuelan.domain.usecase

import com.maclanzh.venezuelan.domain.model.Product

interface GetProductUseCase {
    suspend operator fun invoke(): List<Product>
}