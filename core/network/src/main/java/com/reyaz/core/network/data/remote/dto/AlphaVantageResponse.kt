package com.reyaz.core.network.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AlphaVantageResponse(
    @SerializedName("Information")
    val apiLimitExceeded: String? = null,
    @SerializedName("Meta Data")
    val metaData: MetaData? = null,
    @SerializedName("Time Series (Daily)")
    val timeSeries: Map<String, DailyData>? = null,
    @SerializedName("errorMessage")
    val errorMessage: String? = null,
    @SerializedName("note")
    val note: String? = null
)

data class MetaData(
    @SerializedName("1. Information")
    val information: String,
    @SerializedName("2. Symbol")
    val symbol: String,
    @SerializedName("3. Last Refreshed")
    val lastRefreshed: String,
    @SerializedName("4. Output Size")
    val outputSize: String,
    @SerializedName("5. Time Zone")
    val timeZone: String
)

data class DailyData(
    @SerializedName("1. open")
    val open: String,
    @SerializedName("2. high")
    val high: String,
    @SerializedName("3. low")
    val low: String,
    @SerializedName("4. close")
    val close: String,
    @SerializedName("5. volume")
    val volume: String
)