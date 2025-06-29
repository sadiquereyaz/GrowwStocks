package com.reyaz.core.network.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

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