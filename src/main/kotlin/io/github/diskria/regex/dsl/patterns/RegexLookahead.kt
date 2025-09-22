package io.github.diskria.regex.dsl.patterns

import io.github.diskria.regex.dsl.RegexPattern
import io.github.diskria.regex.dsl.extensions.toRegexPattern

object RegexLookahead {

    fun of(pattern: String): RegexPattern =
        of(pattern.toRegexPattern())

    fun of(pattern: RegexPattern): RegexPattern =
        newInstance(pattern, false)

    fun ofNegated(pattern: String): RegexPattern =
        ofNegated(pattern.toRegexPattern())

    fun ofNegated(pattern: RegexPattern): RegexPattern =
        newInstance(pattern, true)

    fun newInstance(pattern: RegexPattern, isNegative: Boolean = false): RegexPattern =
        RegexAssertion.newInstance(pattern, false, isNegative)
}
