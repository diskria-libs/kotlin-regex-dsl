package io.github.diskria.dsl.regex.ranges

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.utils.kotlin.Constants

object RegexCharacterRange {

    val DIGITS = listOf(RegexDigitsRange)
    val LATIN = listOf(RegexLatinLowercaseRange, RegexLatinUppercaseRange)
    val CYRILLIC = listOf(RegexCyrillicLowercaseRange, RegexCyrillicUppercaseRange)
    val HEX = DIGITS + RegexHexLowercaseRange + RegexHexUppercaseRange

    fun newInstance(rangeChars: Pair<Char, Char>): RegexPattern =
        RegexPattern(
            buildString {
                append(rangeChars.first)
                append(Constants.Char.HYPHEN)
                append(rangeChars.second)
            }
        )
}

val RegexLatinLowercaseRange = RegexCharacterRange.newInstance('a' to 'z')
val RegexLatinUppercaseRange = RegexCharacterRange.newInstance('A' to 'Z')
val RegexCyrillicLowercaseRange = RegexCharacterRange.newInstance('а' to 'я')
val RegexCyrillicUppercaseRange = RegexCharacterRange.newInstance('А' to 'Я')
val RegexDigitsRange = RegexCharacterRange.newInstance('0' to '9')
val RegexHexLowercaseRange = RegexCharacterRange.newInstance('a' to 'f')
val RegexHexUppercaseRange = RegexCharacterRange.newInstance('A' to 'F')
