package com.maclanzh.venezuelan.di

import com.maclanzh.venezuelan.data.remote.ProductApi
import com.maclanzh.venezuelan.data.repository.ProductRepositoryImpl
import com.maclanzh.venezuelan.domain.repository.ProductRepository
import com.maclanzh.venezuelan.domain.usecase.GetProductUseCase
import com.maclanzh.venezuelan.presentation.viewmodel.ProductViewmodel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val productModule = module {
    // data layer
    single { ProductApi(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    // domain layer
    factory { GetProductUseCase(get()) }
    // Presentation Layer
    viewModel { ProductViewmodel(get()) }
}