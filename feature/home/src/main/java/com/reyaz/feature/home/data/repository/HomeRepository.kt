package com.reyaz.feature.home.data.repository

import com.reyaz.core.common.Resource
import com.reyaz.core.common.model.Stock
import com.reyaz.core.common.model.StockType
import com.reyaz.core.database.GrowwDatabase
import com.reyaz.core.database.dao.GrowwDao
import com.reyaz.core.network.data.paging.StocksRemoteRepository
import com.reyaz.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import com.reyaz.core.database.toDomain
import kotlinx.coroutines.flow.flowOn


private const val TAG = "HOME_REPOSITORY_IMPL"

class HomeRepositoryImpl(
    private val db: GrowwDatabase,
    private val growwDao: GrowwDao,
    private val stocksRemoteRepository: StocksRemoteRepository
) : HomeRepository {
    override suspend fun getTopGainerLoser(stockType: StockType): Flow<List<Stock>> =
        growwDao.getTopGainersLosers(stockType)
            .map { list -> list.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)

    override suspend fun refreshStocks(): Flow<Resource<Unit>> =
        stocksRemoteRepository.refreshStocks()
}
