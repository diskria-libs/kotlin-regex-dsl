package io.github.diskria.regex.dsl.combinators

import io.github.diskria.regex.dsl.RegexPattern
import io.github.diskria.regex.dsl.extensions.toRegexPattern
import io.github.diskria.regex.dsl.primitives.RegexEndOfLine
import io.github.diskria.regex.dsl.primitives.RegexStartOfLine

object RegexLine {

    fun of(pattern: String): RegexPattern =
        of(pattern.toRegexPattern())

    fun of(pattern: RegexPattern): RegexPattern =
        RegexPattern(
            buildString {
                append(RegexStartOfLine)
                append(pattern)
                append(RegexEndOfLine)
            }
        )
}
