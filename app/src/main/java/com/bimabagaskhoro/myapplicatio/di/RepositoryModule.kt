package com.bimabagaskhoro.myapplicatio.di

import com.bimabagaskhoro.myapplicatio.data.source.ItemsRepository
import com.bimabagaskhoro.myapplicatio.domain.repository.IItemAlphabetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(itemAlphabetRepository: ItemsRepository): IItemAlphabetRepository
}