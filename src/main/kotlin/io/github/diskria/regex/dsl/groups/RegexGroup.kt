package io.github.diskria.regex.dsl.groups

import io.github.diskria.regex.dsl.RegexPattern
import io.github.diskria.regex.dsl.extensions.buildRegexPattern
import io.github.diskria.regex.dsl.extensions.toRegexPattern
import io.github.diskria.regex.dsl.primitives.RegexCharacterClass
import io.github.diskria.regex.dsl.primitives.RegexWord
import io.github.diskria.regex.dsl.ranges.RegexCharacterRange
import io.github.diskria.utils.kotlin.BracketsType
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.extensions.common.failWithInvalidValue
import io.github.diskria.utils.kotlin.extensions.common.modify
import io.github.diskria.utils.kotlin.extensions.wrapWithBrackets

object RegexGroup {

    internal val nameRegexPattern: RegexPattern by lazy {
        buildRegexPattern {
            append(RegexCharacterClass.of(RegexCharacterRange.LATIN))
            append(RegexWord.zeroOrMore())
        }
    }

    fun of(pattern: String, isCaptured: Boolean): RegexPattern =
        if (isCaptured) ofCaptured(pattern) else of(pattern)

    fun of(pattern: RegexPattern, isCaptured: Boolean): RegexPattern =
        if (isCaptured) ofCaptured(pattern) else of(pattern)

    fun of(pattern: String): RegexPattern =
        of(pattern.toRegexPattern())

    fun of(pattern: RegexPattern): RegexPattern =
        newInstance(pattern, false)

    fun ofCaptured(pattern: String): RegexPattern =
        ofCaptured(pattern.toRegexPattern())

    fun ofCaptured(pattern: RegexPattern): RegexPattern =
        newInstance(pattern, true)

    fun ofNamed(name: String, pattern: String): NamedRegexGroup =
        ofNamed(pattern, name.toRegexPattern())

    fun ofNamed(name: String, pattern: RegexPattern): NamedRegexGroup =
        if (isInvalidName(name)) failWithInvalidValue(name)
        else NamedRegexGroup(
            name,
            newInstance(pattern, true, name),
            RegexBackReference.ofName(name)
        )

    fun newInstance(
        pattern: RegexPattern,
        isCaptured: Boolean = false,
        name: String? = null,
    ): RegexPattern =
        RegexPattern(
            pattern.rawPattern.modify {
                buildString {
                    if (!isCaptured) {
                        append(Constants.Char.QUESTION_MARK)
                        append(Constants.Char.COLON)
                    } else if (name != null) {
                        append(Constants.Char.QUESTION_MARK)
                        append(name.wrapWithBrackets(BracketsType.ANGLE))
                    }
                    append(it)
                }
            }.wrapWithBrackets(BracketsType.ROUND)
        )

    internal fun isInvalidName(name: String): Boolean =
        name.matches(nameRegexPattern.toRegex()).not()
}
