package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CompletableFuture

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnits: TimeUnits): Date {
    var time = this.time

    time += when (timeUnits) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val difTime = Date().time - this.time
    val future : Boolean = difTime < 0

    return when (abs(difTime)) {
        in 0.rangeTo(SECOND) -> "только что"
        in SECOND.rangeTo(SECOND * 45) ->
            if (future) "несколько секунд назад" else "через несколько секунд"
        in (SECOND * 45).rangeTo(SECOND * 75) ->
            if (future) "минуту назад" else "через минуту"
        in (SECOND * 75).rangeTo(MINUTE * 45) ->{
            val min = difTime / MINUTE
            if (future) "$min минут" +
                    " назад" else " "
        }

        in (MINUTE * 45).rangeTo(MINUTE * 75) -> "час назад"
        in (MINUTE * 75).rangeTo(HOUR * 22) -> "${(difTime / HOUR)} часов назад"
        in (HOUR * 22).rangeTo(HOUR * 26) -> "день назад"
        in (HOUR * 26).rangeTo(DAY * 360) -> "${(difTime / DAY)} дней назад"
        in (DAY * 360).rangeTo(Long.MAX_VALUE) -> "более года назад"
        else -> "NOT FUTURE s'ka"
    }
}

private fun butify(value: Int, timeUnits: TimeUnits,future: Boolean):String{


    TimeUnits.SECOND.plural("hehe")
 return " "
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}


fun TimeUnits.plural(time:String):String{

    return time
}


//
//0с - 1с "только что"
//
//1с - 45с "несколько секунд назад"
//
//45с - 75с "минуту назад"
//
//75с - 45мин "N минут назад"
//
//45мин - 75мин "час назад"
//
//75мин 22ч "N часов назад"
//
//22ч - 26ч "день назад"
//
//26ч - 360д "N дней назад"
//
//>360д "более года назад"