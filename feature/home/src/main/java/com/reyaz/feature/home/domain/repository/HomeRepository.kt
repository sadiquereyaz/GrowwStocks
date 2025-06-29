package com.reyaz.feature.home.domain.repository

import com.reyaz.core.common.Resource
import com.reyaz.core.common.model.Stock
import com.reyaz.core.common.model.StockType
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun refreshStocks(): Flow<Resource<Unit>>
    suspend fun getTopGainerLoser(stockType: StockType): Flow<List<Stock>>
}