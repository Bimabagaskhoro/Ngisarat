package com.bimabagaskhoro.myapplicatio.utils

import com.bimabagaskhoro.myapplicatio.data.source.remote.response.DataItem
import com.bimabagaskhoro.myapplicatio.domain.model.ItemAlphabet


object DataMapper {
    fun entitiesToDomain(data: List<DataItem>): List<ItemAlphabet> =
        data.map {
            ItemAlphabet(
                idAlphabet = it.idAlphabet,
                tittleAlphabet = it.tittleAlphabet,
                imageAlphabet = it.imageAlphabet
            )
        }

    fun domainToEntity(data: ItemAlphabet): DataItem =
        DataItem(
            idAlphabet = data.idAlphabet,
            tittleAlphabet = data.tittleAlphabet,
            imageAlphabet = data.imageAlphabet
        )

    fun entityToDomain(data: DataItem): ItemAlphabet =
        ItemAlphabet(
            idAlphabet = data.idAlphabet,
            tittleAlphabet = data.tittleAlphabet,
            imageAlphabet = data.imageAlphabet
        )

}