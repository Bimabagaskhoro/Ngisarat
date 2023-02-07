package com.bimabagaskhoro.myapplicatio.domain.repository

import com.bimabagaskhoro.myapplicatio.data.Resource
import com.bimabagaskhoro.myapplicatio.domain.model.ItemAlphabet
import kotlinx.coroutines.flow.Flow

interface IItemAlphabetRepository {

    fun getAllAlphabet(): Flow<Resource<List<ItemAlphabet>>>

    fun getAlphabetById(id :Int): Flow<Resource<List<ItemAlphabet>>>
}