package com.bimabagaskhoro.myapplicatio.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseSuccess(
	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: Status
) : Parcelable

@Parcelize
data class DataItem(
	@field:SerializedName("tittle_alphabet")
	val tittleAlphabet: String,

	@field:SerializedName("image_alphabet")
	val imageAlphabet: String,

	@field:SerializedName("id_alphabet")
	val idAlphabet: String
) : Parcelable

@Parcelize
data class Status(
	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String
) : Parcelable
