package com.bimabagaskhoro.myapplicatio.data.source.remote

import android.util.Log
import com.bimabagaskhoro.myapplicatio.data.source.remote.response.DataItem
import com.bimabagaskhoro.myapplicatio.data.source.remote.network.ApiResponse
import com.bimabagaskhoro.myapplicatio.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    private val TAG = RemoteDataSource::class.java.simpleName

    suspend fun getAllAlphabet(): Flow<ApiResponse<List<DataItem>>> {
        return flow {
            try {
                val response = apiService.getAllAlphabet()
                val data = response.data
                if (response.status.code == 200) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.d(TAG, "getAllAlphabet: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAlphabetById(id: Int): Flow<ApiResponse<List<DataItem>>> {
        return flow {
            try {
                val response = apiService.getAlphabetById(id)
                val data = response.data
                if (response.status.code == 200) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.d(TAG, "getAlphabetById: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }
}