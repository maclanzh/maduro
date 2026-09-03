package com.maclanzh.venezuelan.domain.repository

import com.maclanzh.venezuelan.domain.model.Product

interface ProductRepository {
    suspend fun getProducts() : List<Product>
}