package com.maclanzh.venezuelan.data.remote

import com.maclanzh.venezuelan.domain.model.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// httpClient
val client = HttpClient(CIO){
    install(ContentNegotiation){
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
}

// api calls

suspend fun fetchProducts() : List<Product> {
    return client.get("https://fakestoreapi.com/products").body()
}

