package io.github.diskria.kotlin.regex.dsl.primitives

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.utils.Constants

object RegexEscape {

    fun newInstance(char: Char): RegexPattern =
        RegexPattern(
            buildString {
                append(Constants.Char.BACK_SLASH)
                append(char)
            }
        )
}

val RegexEscapeStart = RegexEscape.newInstance('Q')
val RegexEscapeEnd = RegexEscape.newInstance('E')
val RegexNewLine = RegexEscape.newInstance('n')
val RegexTab = RegexEscape.newInstance('t')
