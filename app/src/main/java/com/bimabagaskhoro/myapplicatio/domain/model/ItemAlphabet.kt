package com.bimabagaskhoro.myapplicatio.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemAlphabet(
    @field:SerializedName("id_alphabet")
    val idAlphabet: String,

    @field:SerializedName("tittle_alphabet")
    val tittleAlphabet: String,

    @field:SerializedName("image_alphabet")
    val imageAlphabet: String
) : Parcelable
