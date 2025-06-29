package com.reyaz.core.common.utils

import android.util.Log
import com.reyaz.core.common.model.Stock
import java.math.RoundingMode
import kotlin.math.abs

private const val TAG = "CONVERSION"

object TypeConvertor {
    fun roundOffString(decimalString: String): Float? {
        return try {
            var pureNum = decimalString.trim()
            val isPercent = pureNum.endsWith("%")
            if (isPercent) {
                pureNum = pureNum.removeSuffix("%")
            }

            val number = pureNum.toBigDecimalOrNull() ?: return null

            // Round to 2 decimal places
            val rounded = number.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros()

            // Convert to float for return
            var result = rounded.toPlainString().toFloat()

            if (isPercent) {
                result = abs(result)
            }

            Log.d(TAG, "roundOffString: $decimalString -> $result")
            result
        } catch (e: Exception) {
            Log.e(TAG, "roundOffString error for '$decimalString': ${e.message}")
            null
        }
    }

}