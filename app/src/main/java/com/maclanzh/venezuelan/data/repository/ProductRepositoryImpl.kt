package com.maclanzh.venezuelan.data.repository

import com.maclanzh.venezuelan.data.remote.ProductApi
import com.maclanzh.venezuelan.domain.model.Product
import com.maclanzh.venezuelan.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api : ProductApi
)  : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return api.fetchProducts()
    }
}