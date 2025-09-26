package io.github.diskria.kotlin.regex.dsl.primitives

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.extensions.toRegexPattern
import io.github.diskria.kotlin.utils.BracketsType
import io.github.diskria.kotlin.utils.Constants
import io.github.diskria.kotlin.utils.EscapeMode
import io.github.diskria.kotlin.utils.extensions.common.modifyIf
import io.github.diskria.kotlin.utils.extensions.generics.toFlatString
import io.github.diskria.kotlin.utils.extensions.primitives.escaped

object RegexCharacterClass {

    fun of(vararg chars: Char): RegexPattern =
        newInstance(chars = chars)

    fun ofNegated(vararg chars: Char): RegexPattern =
        newInstance(isNegated = true, chars = chars)

    fun of(vararg patterns: RegexPattern): RegexPattern =
        newInstance(patterns = patterns.toList())

    fun ofNegated(vararg patterns: RegexPattern): RegexPattern =
        newInstance(patterns = patterns.toList(), isNegated = true)

    fun of(patterns: List<RegexPattern>, vararg chars: Char): RegexPattern =
        newInstance(patterns = patterns, chars = chars)

    fun ofNegated(patterns: List<RegexPattern>, vararg chars: Char): RegexPattern =
        newInstance(patterns = patterns, chars = chars, isNegated = true)

    fun newInstance(
        patterns: List<RegexPattern> = emptyList(),
        isNegated: Boolean = false,
        vararg chars: Char,
    ): RegexPattern =
        run {
            val rangesPattern = patterns.toFlatString { it.toString() }
            val charsPattern = chars.asIterable().toFlatString {
                it.escaped(EscapeMode.REGEX_CHARACTER_CLASS)
            }
            (rangesPattern + charsPattern).modifyIf(isNegated) { Constants.Char.CARET + it }
        }.toRegexPattern().wrapWithBrackets(BracketsType.SQUARE, shouldEscape = false)
}
