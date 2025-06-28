package com.reyaz.core.network.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GainerLoserResponse(
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    @SerialName("metadata")
    val metadata: String? = null,
    @SerialName("top_gainers")
    val topGainers: List<Stock?>? = null,
    @SerialName("top_losers")
    val topLosers: List<Stock?>? = null
)


@Serializable
data class Stock(
    @SerialName("change_amount")
    val changeAmount: String? = null,
    @SerialName("change_percentage")
    val changePercentage: String? = null,
    @SerialName("price")
    val price: String? = null,
    @SerialName("ticker")
    val ticker: String? = null,
    @SerialName("volume")
    val volume: String? = null
)



