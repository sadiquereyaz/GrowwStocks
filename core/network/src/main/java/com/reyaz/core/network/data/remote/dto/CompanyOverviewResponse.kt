package com.reyaz.core.network.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyOverviewResponse(
    @SerialName("Symbol") val symbol: String? = null,
    @SerialName("Name") val name: String? = null,
    @SerialName("AssetType") val assetType: String? = null,
    @SerialName("Description") val description: String? = null,
    @SerialName("Exchange") val exchange: String? = null,
    @SerialName("Currency") val currency: String? = null,
    @SerialName("Country") val country: String? = null,
    @SerialName("Sector") val sector: String? = null,
    @SerialName("Address") val address: String? = null,
    @SerialName("OfficialSite") val website: String? = null,
)
