@file:Suppress("LiftReturnOrAssignment")

package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        return when {
            parts?.count() == 2 -> Pair(parts[0], parts[1])
            parts?.count() == 1 && parts[0] != "" -> Pair(parts[0], null)
            else -> Pair(null, null)
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val fn = firstName?.trim()?.getOrNull(0)
        val ln = lastName?.trim()?.getOrNull(0)

        return when {
            fn != null && ln != null -> "${fn.toUpperCase()}${ln.toUpperCase()}"
            fn != null -> "${fn.toUpperCase()}"
            else -> null
        }
    }


    fun transliteration(payload: String?, divider: String? = " "):String{

        val(firstName,lastName) = parseFullName(payload)
        return "${this.translit(firstName)}$divider${this.translit(lastName)}"
    }

   private fun translit(string: String?): String {

       var tmpString = ""

        val key = mapOf(
            "а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z",
            "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r",
            "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh",
            "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya"
        )

       string?.forEach { c: Char ->
        tmpString += key[c.toString().toLowerCase()] ?: c
       }

       return tmpString.capitalize()
    }
}