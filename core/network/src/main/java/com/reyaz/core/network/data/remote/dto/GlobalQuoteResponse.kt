package com.reyaz.core.network.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GlobalQuoteResponse(
    @SerialName("Global Quote")
    val globalQuote: GlobalQuote
)

@Serializable
data class GlobalQuote(
    @SerialName("05. price") val price: String,
    @SerialName("09. change") val change: String,
    @SerialName("10. change percent") val changePercent: String
)
