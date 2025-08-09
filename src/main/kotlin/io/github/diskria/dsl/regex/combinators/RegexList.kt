package io.github.diskria.dsl.regex.combinators

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.extensions.buildRegexPattern
import io.github.diskria.dsl.regex.extensions.toRegexPattern
import io.github.diskria.dsl.regex.groups.RegexGroup
import io.github.diskria.utils.kotlin.Constants

object RegexList {

    fun of(
        element: String,
        separator: Char,
        prefix: String = Constants.Char.EMPTY
    ): RegexPattern =
        of(element.toRegexPattern(), separator, prefix)

    fun of(
        element: RegexPattern,
        separator: Char,
        prefix: String = Constants.Char.EMPTY
    ): RegexPattern =
        of(element, separator.toString(), prefix)

    fun of(
        element: RegexPattern,
        separator: String,
        prefix: String = Constants.Char.EMPTY
    ): RegexPattern =
        of(element, separator.toRegexPattern(), prefix)

    fun of(
        element: RegexPattern,
        separator: RegexPattern,
        prefix: String = Constants.Char.EMPTY
    ): RegexPattern =
        newInstance(prefix, element, separator)

    fun newInstance(prefix: String, element: RegexPattern, separator: RegexPattern): RegexPattern =
        RegexPattern(
            buildString {
                append(prefix)
                append(element)
                append(
                    RegexGroup.of(
                        buildRegexPattern {
                            append(separator)
                            append(element)
                        }
                    ).zeroOrMore()
                )
            }
        )
}
