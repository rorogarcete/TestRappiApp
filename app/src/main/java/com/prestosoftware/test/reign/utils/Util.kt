package com.prestosoftware.test.rappi.util

import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

internal const val HOURS24_IN_SECONDS = 86400
internal const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

fun secondsBetweenTwoDates(dateString: String, dateStringTwo: String): Long {
    return getDifferenceTimeUnitBetweenDates(
        dateString,
        dateStringTwo,
        DATE_FORMAT,
        TimeUnit.SECONDS
    )
}

fun currentDateString(): String = SimpleDateFormat(DATE_FORMAT, Locale.US).format(Date())

private fun getDifferenceTimeUnitBetweenDates(
    initialDate: String,
    endDate: String,
    dateFormat: String,
    timeUnit: TimeUnit
): Long {
    val format = SimpleDateFormat(dateFormat, Locale.US)
    return tryOrDefault(
        {
            val date1 = format.parse(initialDate)
            val date2 = format.parse(endDate)
            val diff = date2.time - date1.time
            timeUnit.convert(diff, TimeUnit.MILLISECONDS)
        },
        -1
    )
}

internal fun shouldShowCountdown(secondsBetween: Long): Boolean {
    return (secondsBetween <= HOURS24_IN_SECONDS)
}

internal fun getTimeInSeconds(endsAt: String?): Long {
    return endsAt?.let { secondsBetweenTwoDates(currentDateString(), it) } ?: 0L
}

fun Long.formattedTimeLeft(showDayFormat: Boolean = false): SpannableString {
    val days = TimeUnit.SECONDS.toDays(this).toInt()
    val hours = TimeUnit.SECONDS.toHours(this) - days * 24
    val minutes = TimeUnit.SECONDS.toMinutes(this) - TimeUnit.SECONDS.toHours(this) * 60

    val result: String
    val timeFormatlist: List<Int>
    val daysIndex: Int
    val hoursIndex: Int
    val minutesIndex: Int

    if (showDayFormat) {
        result = String.format("%02dD : %02dH : %02dM", days, hours, minutes)
        daysIndex = result.indexOf('D')
        hoursIndex = result.indexOf('H')
        minutesIndex = result.indexOf('M')
        timeFormatlist = listOf(daysIndex, daysIndex + 4, hoursIndex, hoursIndex + 4, minutesIndex, minutesIndex + 1)
    } else {
        result = String.format("%02dH : %02dM", hours, minutes)
        hoursIndex = result.indexOf('H')
        minutesIndex = result.indexOf('M')
        timeFormatlist = listOf(hoursIndex, hoursIndex + 2, minutesIndex, minutesIndex + 1)
    }

    return SpannableString(result).applySpans(
        timeFormatlist,
        sizeSpan = RelativeSizeSpan(0.7f), styleSpan = StyleSpan(Typeface.NORMAL)
    )
}

fun SpannableString.applySpans(
    indexes: List<Int>,
    colorSpan: ForegroundColorSpan? = null,
    styleSpan: StyleSpan? = null,
    strikeThroughSpan: StrikethroughSpan? = null,
    sizeSpan: RelativeSizeSpan? = null,
    absoluteSizeSpan: AbsoluteSizeSpan? = null,
    underlineSpan: UnderlineSpan? = null
): SpannableString {
    if (indexes.size > 1) {
        var index = 0
        while (index + 1 < indexes.size) {
            colorSpan?.let {
                this.setSpan(
                    ForegroundColorSpan(it.foregroundColor),
                    indexes[index],
                    indexes[index + 1],
                    0
                )
            }
            styleSpan?.let {
                this.setSpan(
                    StyleSpan(it.style),
                    indexes[index],
                    indexes[index + 1],
                    0
                )
            }
            underlineSpan?.let { this.setSpan(it, indexes[index], indexes[index + 1], 0) }
            strikeThroughSpan?.let {
                this.setSpan(
                    StrikethroughSpan(),
                    indexes[index],
                    indexes[index + 1],
                    0
                )
            }
            sizeSpan?.let {
                this.setSpan(
                    RelativeSizeSpan(it.sizeChange),
                    indexes[index],
                    indexes[index + 1],
                    0
                )
            }
            absoluteSizeSpan?.let {
                this.setSpan(
                    AbsoluteSizeSpan(it.size),
                    indexes[index],
                    indexes[index + 1],
                    0
                )
            }
            index += 2
        }
    }
    return this
}


inline fun <T> tryOrDefault(f: () -> T, defaultValue: T): T {
    return try {
        f()
    } catch (e: Exception) {
        defaultValue
    }
}



