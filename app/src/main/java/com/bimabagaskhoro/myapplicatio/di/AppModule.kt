package com.bimabagaskhoro.myapplicatio.di

import com.bimabagaskhoro.myapplicatio.domain.usecase.ItemAlphabetInteractor
import com.bimabagaskhoro.myapplicatio.domain.usecase.ItemAlphabetUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideItemGuestUseCase(itemAlphabetInteractor: ItemAlphabetInteractor): ItemAlphabetUseCase
}