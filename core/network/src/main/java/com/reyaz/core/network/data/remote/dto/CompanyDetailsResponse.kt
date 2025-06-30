package com.reyaz.core.network.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class CompanyDetailsResponse(
    @SerializedName("companyName")
    val companyName: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,

/*
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("beta")
    val beta: Int? = null,
    @SerializedName("ceo")
    val ceo: String? = null,
    @SerializedName("changes")
    val changes: Double? = null,
    @SerializedName("cik")
    val cik: String? = null,
    @SerializedName("city")
    val city: String? = null,

    @SerializedName("country")
    val country: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("cusip")
    val cusip: Any? = null,
    @SerializedName("dcf")
    val dcf: Int? = null,
    @SerializedName("dcfDiff")
    val dcfDiff: Any? = null,
    @SerializedName("defaultImage")
    val defaultImage: Boolean? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("exchange")
    val exchange: String? = null,
    @SerializedName("exchangeShortName")
    val exchangeShortName: String? = null,
    @SerializedName("fullTimeEmployees")
    val fullTimeEmployees: Any? = null,
    @SerializedName("industry")
    val industry: String? = null,
    @SerializedName("ipoDate")
    val ipoDate: String? = null,
    @SerializedName("isActivelyTrading")
    val isActivelyTrading: Boolean? = null,
    @SerializedName("isAdr")
    val isAdr: Boolean? = null,
    @SerializedName("isEtf")
    val isEtf: Boolean? = null,
    @SerializedName("isFund")
    val isFund: Boolean? = null,
    @SerializedName("isin")
    val isin: String? = null,
    @SerializedName("lastDiv")
    val lastDiv: Int? = null,
    @SerializedName("mktCap")
    val mktCap: Int? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("range")
    val range: String? = null,
    @SerializedName("sector")
    val sector: String? = null,
    @SerializedName("state")
    val state: Any? = null,
    @SerializedName("volAvg")
    val volAvg: Int? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("zip")
    val zip: String? = null
*/
)


