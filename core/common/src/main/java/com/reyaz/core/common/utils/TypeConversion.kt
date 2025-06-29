package com.reyaz.core.common.utils

import android.util.Log
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.abs

private const val TAG = "CONVERSION"

object TypeConversion {
    fun roundOffString(decimalString: String): Float? {
        try {
            val pureNum = decimalString.trim()
            val isPercent = pureNum.endsWith("%")
            if (isPercent)
                pureNum.removeSuffix("%")

            val number = pureNum.toBigDecimalOrNull()

            var roundedNumber = number?.setScale(2, RoundingMode.HALF_UP)?.toFloat()
            if (roundedNumber != null && isPercent)
                roundedNumber = abs(roundedNumber)
            Log.d(TAG, "roundOffString: $decimalString -> $roundedNumber")
            return roundedNumber

        } catch (e: NumberFormatException) {
            // Handle cases where the input string is not a valid number
            println("Error: Invalid number format for input: '$decimalString'")
            return null
        } catch (e: Exception) {
            // Catch any other unexpected errors
            println("An unexpected error occurred: ${e.message}")
            return null
        }
    }
}