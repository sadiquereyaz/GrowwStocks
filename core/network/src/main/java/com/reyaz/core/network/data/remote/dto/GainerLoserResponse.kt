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
    val topGainers: List<Stock?>? = null,
    @SerializedName("top_losers")
    val topLosers: List<Stock?>? = null
)


@Serializable
data class Stock(
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



