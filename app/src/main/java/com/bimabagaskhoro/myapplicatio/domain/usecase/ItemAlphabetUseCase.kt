package com.bimabagaskhoro.myapplicatio.domain.usecase

import com.bimabagaskhoro.myapplicatio.domain.model.ItemAlphabet
import com.bimabagaskhoro.myapplicatio.data.Resource
import kotlinx.coroutines.flow.Flow

interface ItemAlphabetUseCase {
    fun getAllAlphabet(): Flow<Resource<List<ItemAlphabet>>>

    fun getAlphabetById(id :Int): Flow<Resource<List<ItemAlphabet>>>
}