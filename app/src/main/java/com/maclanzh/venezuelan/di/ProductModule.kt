package com.maclanzh.venezuelan.di

import com.maclanzh.venezuelan.data.remote.ProductApi
import com.maclanzh.venezuelan.data.repository.ProductRepositoryImpl
import com.maclanzh.venezuelan.domain.repository.ProductRepository
import com.maclanzh.venezuelan.domain.usecase.GetProductUseCase
import com.maclanzh.venezuelan.presentation.viewmodel.ProductViewmodel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val productModule = module {
    single { ProductApi(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    factory { GetProductUseCase(get()) }
    viewModel { ProductViewmodel(get()) }
}