package io.github.diskria.kotlin.regex.dsl.combinators

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.extensions.toRegexPattern
import io.github.diskria.kotlin.regex.dsl.primitives.RegexEndOfLine
import io.github.diskria.kotlin.regex.dsl.primitives.RegexStartOfLine

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
