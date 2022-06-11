package com.bimabagaskhoro.myapplicatio.domain.usecase

import com.bimabagaskhoro.myapplicatio.domain.model.ItemAlphabet
import com.bimabagaskhoro.myapplicatio.data.Resource
import com.bimabagaskhoro.myapplicatio.domain.repository.IItemAlphabetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemAlphabetInteractor @Inject constructor(private val itemAlphabetRepository: IItemAlphabetRepository) :
    ItemAlphabetUseCase {
    override fun getAllAlphabet(): Flow<Resource<List<ItemAlphabet>>> =
        itemAlphabetRepository.getAllAlphabet()

    override fun getAlphabetById(id: Int): Flow<Resource<List<ItemAlphabet>>> =
        itemAlphabetRepository.getAlphabetById(id)
}