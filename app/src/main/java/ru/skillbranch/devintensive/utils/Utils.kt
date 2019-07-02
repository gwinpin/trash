@file:Suppress("LiftReturnOrAssignment")

package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val tmpFullName: Pair<String?, String?>
        val parts: List<String>? = fullName?.split(" ")

        when {
            parts?.count() == 2 -> tmpFullName = Pair(parts[0], parts[1])
            parts?.count() == 1 && parts[0] !== "" -> tmpFullName = Pair(parts[0], null)
            else -> tmpFullName = Pair(null, null)
        }

        return tmpFullName
    }
}