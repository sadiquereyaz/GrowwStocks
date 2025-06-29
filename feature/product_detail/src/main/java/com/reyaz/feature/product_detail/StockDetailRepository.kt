package com.reyaz.feature.product_detail

import com.reyaz.core.common.Resource
import com.reyaz.core.network.data.remote.api.AlphaVantageApiService
import com.reyaz.feature.product_detail.domain.StockDetail
import com.reyaz.feature.product_detail.domain.StockDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StockDetailRepositoryImpl(
    private val apiService: AlphaVantageApiService
) : StockDetailRepository {

    /*override fun fetchStockDetail(ticker: String): Flow<Resource<StockDetail>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiService.fetchStockDetail(ticker)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Resource.Success(body.toDomain()))
                } else {
                    emit(Resource.Error("No data available for $ticker"))
                }
            } else {
                emit(Resource.Error("Error ${response.code()}: ${response.message()}"))
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)*/
}
