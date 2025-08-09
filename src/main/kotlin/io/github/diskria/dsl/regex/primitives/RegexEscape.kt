package io.github.diskria.dsl.regex.primitives

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.utils.kotlin.Constants

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
