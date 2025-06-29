package com.reyaz.core.network.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GainerLoserResponse(
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    @SerializedName("metadata")
    val metadata: String? = null,
    @SerializedName("top_gainers")
    val topGainers: List<StockDto?>? = null,
    @SerializedName("top_losers")
    val topLosers: List<StockDto?>? = null
)


@Serializable
data class StockDto(
    @SerializedName("change_amount")
    val changeAmount: String? = null,
    @SerializedName("change_percentage")
    val changePercentage: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("ticker")
    val ticker: String? = null,
    @SerializedName("volume")
    val volume: String? = null
)



