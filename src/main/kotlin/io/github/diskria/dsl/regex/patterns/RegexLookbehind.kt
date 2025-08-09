package io.github.diskria.dsl.regex.patterns

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.extensions.toRegexPattern

object RegexLookbehind {

    fun of(pattern: String): RegexPattern =
        of(pattern.toRegexPattern())

    fun of(pattern: RegexPattern): RegexPattern =
        newInstance(pattern, false)

    fun ofNegated(pattern: String): RegexPattern =
        ofNegated(pattern.toRegexPattern())

    fun ofNegated(pattern: RegexPattern): RegexPattern =
        newInstance(pattern, true)

    fun newInstance(pattern: RegexPattern, isNegative: Boolean = false): RegexPattern =
        RegexAssertion.newInstance(pattern, true, isNegative)
}
