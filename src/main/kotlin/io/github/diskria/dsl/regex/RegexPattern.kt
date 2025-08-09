package io.github.diskria.dsl.regex

import io.github.diskria.dsl.regex.extensions.buildRegexPattern
import io.github.diskria.dsl.regex.extensions.replaceRegex
import io.github.diskria.dsl.regex.extensions.toRegexPattern
import io.github.diskria.dsl.regex.primitives.RegexAnyChar
import io.github.diskria.dsl.regex.primitives.RegexEscapeEnd
import io.github.diskria.dsl.regex.primitives.RegexEscapeStart
import io.github.diskria.utils.kotlin.BracketsType
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.EscapeMode
import io.github.diskria.utils.kotlin.delegates.toAutoNamedPair
import io.github.diskria.utils.kotlin.extensions.common.failWithDetails
import io.github.diskria.utils.kotlin.extensions.common.failWithWrongUsage
import io.github.diskria.utils.kotlin.extensions.common.modifyIf
import io.github.diskria.utils.kotlin.extensions.primitives.orZero
import io.github.diskria.utils.kotlin.extensions.wrap
import io.github.diskria.utils.kotlin.extensions.wrapWithBrackets

class RegexPattern(internal val rawPattern: String) {

    fun optional(isLazy: Boolean = false): RegexPattern =
        applyQuantifier(Constants.Char.QUESTION_MARK, isLazy)

    fun oneOrMore(isLazy: Boolean = false): RegexPattern =
        applyQuantifier(Constants.Char.PLUS, isLazy)

    fun zeroOrMore(isLazy: Boolean = false): RegexPattern =
        applyQuantifier(Constants.Char.ASTERISK, isLazy)

    fun counts(count: Int, isLazy: Boolean = false): RegexPattern =
        countsRange(count, count)

    fun single(): RegexPattern =
        this

    fun countsRange(min: Int? = null, max: Int? = null, isLazy: Boolean = false): RegexPattern =
        when {
            min == null && max == null -> failWithWrongUsage(useInsteadThis = "zeroOrMore()")
            min != null && max != null && min > max -> {
                val min by min.toAutoNamedPair()
                val max by max.toAutoNamedPair()
                failWithDetails("max must be > min", min, max)
            }

            else -> {
                val quantifier = buildString {
                    if (min == max) {
                        append(min)
                    } else {
                        append(min.orZero())
                        append(Constants.Char.COMMA)
                        max?.let { append(it) }
                    }
                }
                append(quantifier.wrapWithBrackets(BracketsType.CURLY))
            }
        }

    fun toRegex(): Regex =
        rawPattern.toRegex()

    fun wrap(char: Char): RegexPattern =
        wrap(char.toString())

    fun wrap(string: String): RegexPattern =
        rawPattern.wrap(string).toRegexPattern()

    fun wrapWithBrackets(
        bracketsType: BracketsType?,
        count: Int = 1,
        shouldEscape: Boolean = true,
    ): RegexPattern =
        rawPattern
            .wrapWithBrackets(
                bracketsType,
                count,
                escapeMode = run {
                    if (shouldEscape) EscapeMode.REGEX
                    else null
                }
            )
            .toRegexPattern()

    fun getHumanReadablePattern(): String =
        rawPattern.replaceRegex(
            buildRegexPattern {
                append(RegexEscapeStart)
                append(RegexAnyChar.zeroOrMore().optional())
                append(RegexEscapeEnd)
            }
        ) { escapedSegment ->
            escapedSegment
                .removePrefix(RegexEscapeStart.toString())
                .removeSuffix(RegexEscapeEnd.toString())
        }

    fun append(other: String): RegexPattern =
        rawPattern.plus(other).toRegexPattern()

    override fun toString(): String =
        rawPattern

    private fun applyQuantifier(quantifier: Char, isLazy: Boolean): RegexPattern =
        append(quantifier.toString().modifyIf(isLazy) { it + Constants.Char.QUESTION_MARK })

    companion object {
        fun of(pattern: Char): RegexPattern =
            of(pattern.toString())

        fun of(pattern: String): RegexPattern =
            RegexPattern(pattern)
    }
}
