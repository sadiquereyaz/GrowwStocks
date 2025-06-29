package com.reyaz.feature.product_list.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.reyaz.core.common.model.Stock
import com.reyaz.core.common.model.StockType
import com.reyaz.core.database.dao.GrowwDao
import com.reyaz.core.database.toDomain
import com.reyaz.feature.product_list.domain.repository.StockListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StockListRepositoryImpl(
    private val growwDao: GrowwDao
) : StockListRepository {
    override fun getPagedStocks(type: StockType): Flow<PagingData<Stock>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { growwDao.pagingSource(type) }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }
    }
}