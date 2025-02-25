package com.example.loopmovieapp.com.example.loopmovieapp.util

import android.util.Log
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun formatMoney(value: Int): String {
    val format = DecimalFormat("#,###")
    return format.format(value)
}

fun convertRuntimeToHoursAndMinutes(runtimeInMinutes: Int): String {
    val hours = runtimeInMinutes / 60
    val minutes = runtimeInMinutes % 60
    return "${hours}h ${minutes}m"
}

fun formatDate(originalDate: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy/MM/dd", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val date = inputFormat.parse(originalDate)

        date?.let { outputFormat.format(it) } ?: run {
            Log.e("DateFormat", "Date parsing failed for: $originalDate")
            originalDate
        }
    } catch (e: Exception) {
        Log.e("DateFormat", "Error parsing date: $originalDate", e)
        originalDate
    }
}

fun roundToTwoDecimals(value: Double): Double {
    return BigDecimal(value).setScale(2, RoundingMode.HALF_UP).toDouble()
}

fun getLanguageFullName(code: String): String {
    return when (code.lowercase(Locale.ROOT)) {
        "en" -> "English"
        "fr" -> "French"
        "es" -> "Spanish"
        "de" -> "German"
        "it" -> "Italian"
        "pt" -> "Portuguese"
        "zh" -> "Chinese"
        "ja" -> "Japanese"
        "ru" -> "Russian"
        "ar" -> "Arabic"
        else -> code
    }
}

