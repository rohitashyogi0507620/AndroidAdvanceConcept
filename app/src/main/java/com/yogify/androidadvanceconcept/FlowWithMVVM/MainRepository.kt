package com.yogify.androidadvanceconcept.FlowWithMVVM

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getProductListMovies()  = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getProduct()
        emit(NetworkResult.Success(response.body()!!))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}