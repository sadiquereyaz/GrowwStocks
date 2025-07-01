package com.reyaz.feature.product_detail.domain

data class CompanyDetail(
    val symbol: String,
    val name: String,
    val exchange: String,
    val industry: String,
    val website: String,
    val description: String,
    val ceo: String,
    val securityName: String,
    val issueType: String,
    val sector: String,
    val country: String,
    val fullTimeEmployees: Int,
)
