package com.bimabagaskhoro.myapplicatio.data.source

import android.util.Log
import com.bimabagaskhoro.myapplicatio.domain.model.ItemAlphabet
import com.bimabagaskhoro.myapplicatio.domain.repository.IItemAlphabetRepository
import com.bimabagaskhoro.myapplicatio.data.Resource
import com.bimabagaskhoro.myapplicatio.data.source.remote.RemoteDataSource
import com.bimabagaskhoro.myapplicatio.utils.DataMapper
import com.bimabagaskhoro.myapplicatio.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemsRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IItemAlphabetRepository {

    private val TAG = ItemsRepository::class.java.simpleName
    override fun getAllAlphabet(): Flow<Resource<List<ItemAlphabet>>> {
        return flow {
            when (val apiResponse = remoteDataSource.getAllAlphabet().first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.entitiesToDomain(apiResponse.data!!)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage!!))
                }
                is ApiResponse.Empty -> {
                    Log.d(TAG, "getAllAlphabet: Empty Data")
                }
            }
        }
    }

    override fun getAlphabetById(id: Int): Flow<Resource<List<ItemAlphabet>>> {
        return flow {
            when (val apiResponse = remoteDataSource.getAlphabetById(id).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.entitiesToDomain(apiResponse.data!!)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage!!))
                }
                is ApiResponse.Empty -> {
                    Log.d(TAG, "getAlphabetById: Empty Data")
                }
            }
        }
    }
}