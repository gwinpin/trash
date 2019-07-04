package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun TimeUnits.plural(value: Int): String {
    val absVal = abs(value)

    val triple: Triple<String, String, String> = when (this) {
        TimeUnits.SECOND -> Triple("секунд", "секунды", "секунду")
        TimeUnits.MINUTE -> Triple("минут", "минуты", "минуту")
        TimeUnits.HOUR -> Triple("часов", "часа", "час")
        TimeUnits.DAY -> Triple("дней", "дня", "день")
    }

    return "$absVal " + when {
        absVal % 100  in 11..19 -> triple.first
        absVal % 10  in 2..4 -> triple.second
        absVal % 10  == 1 -> triple.third
        else -> triple.first
    }
}


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnits: TimeUnits): Date {
    this.time += when (timeUnits) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val difTime = this.time - date.time
    val pair: Pair<Boolean, String> = Pair(difTime < 0, if (difTime < 0) " назад" else "через ")

    val txt: String = when (abs(difTime)) {
        in 0.rangeTo(SECOND) -> "только что"
        in SECOND.rangeTo(SECOND * 45) -> "несколько секунд"
        in (SECOND * 45).rangeTo(SECOND * 75) -> "минуту"
        in (SECOND * 75).rangeTo(MINUTE * 45) -> TimeUnits.MINUTE.plural((difTime / MINUTE).toInt())
        in (MINUTE * 45).rangeTo(MINUTE * 75) -> "час назад"
        in (MINUTE * 75).rangeTo(HOUR * 22) -> TimeUnits.HOUR.plural((difTime / HOUR).toInt())
        in (HOUR * 22).rangeTo(HOUR * 26) -> "день назад"
        in (HOUR * 26).rangeTo(DAY * 360) -> TimeUnits.DAY.plural((difTime / DAY).toInt())
        in (DAY * 360).rangeTo(Long.MAX_VALUE) -> "более года"
        else -> "NOT FUTURE s'ka"
    }

    return if (txt == "более года" && !pair.first) "более чем через год"
    else if (pair.first) txt + pair.second else pair.second + txt
}

