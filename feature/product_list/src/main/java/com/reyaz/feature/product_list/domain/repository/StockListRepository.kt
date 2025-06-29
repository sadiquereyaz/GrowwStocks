package com.reyaz.feature.product_list.domain.repository

import androidx.paging.PagingData
import com.reyaz.core.common.model.Stock
import com.reyaz.core.common.model.StockType
import kotlinx.coroutines.flow.Flow

interface StockListRepository {
    fun getPagedStocks(type: StockType): Flow<PagingData<Stock>>

}