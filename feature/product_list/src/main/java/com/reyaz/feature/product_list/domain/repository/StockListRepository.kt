package com.reyaz.feature.product_list.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.reyaz.core.common.model.Stock
import kotlinx.coroutines.flow.Flow

interface StockListRepository {
    fun getPagedStocks(): Flow<PagingData<Stock>>

}