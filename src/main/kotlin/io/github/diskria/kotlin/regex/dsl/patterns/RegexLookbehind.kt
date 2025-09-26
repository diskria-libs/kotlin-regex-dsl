package io.github.diskria.kotlin.regex.dsl.patterns

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.extensions.toRegexPattern

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
