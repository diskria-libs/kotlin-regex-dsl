package io.github.diskria.dsl.regex.combinators

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.extensions.toRegexPattern
import io.github.diskria.dsl.regex.primitives.RegexEndOfLine
import io.github.diskria.dsl.regex.primitives.RegexStartOfLine

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
