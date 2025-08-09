package io.github.diskria.dsl.regex.primitives

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.extensions.common.modifyIf

object RegexPredefinedClass {

    fun newInstance(char: Char, isNegated: Boolean = false): RegexPattern =
        RegexPattern(
            buildString {
                append(Constants.Char.BACK_SLASH)
                append(char.modifyIf(isNegated) { it.uppercaseChar() })
            }
        )
}

val RegexDigit = digit(false)
val RegexNonDigit = digit(true)
val RegexWord = word(false)
val RegexNonWord = word(true)
val RegexWhitespace = whitespace(false)
val RegexNonWhitespace = whitespace(true)

internal val RegexNamedGroupBackReference = RegexPredefinedClass.newInstance('k')

private fun digit(isNegated: Boolean) = RegexPredefinedClass.newInstance('d', isNegated)
private fun whitespace(isNegated: Boolean) = RegexPredefinedClass.newInstance('s', isNegated)
private fun word(isNegated: Boolean) = RegexPredefinedClass.newInstance('w', isNegated)
