package io.github.diskria.dsl.regex.patterns

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.extensions.toRegexPattern
import io.github.diskria.utils.kotlin.BracketsType
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.extensions.wrapWithBrackets

object RegexAssertion {

    fun newInstance(
        pattern: RegexPattern,
        isBehind: Boolean,
        isNegative: Boolean,
    ): RegexPattern =
        buildString {
            append(Constants.Char.QUESTION_MARK)
            if (isBehind) {
                append(Constants.Char.OPENING_ANGLE_BRACKET)
            }
            append(
                if (isNegative) Constants.Char.EXCLAMATION_MARK
                else Constants.Char.EQUAL_SIGN
            )
            append(pattern)
        }.wrapWithBrackets(BracketsType.ROUND).toRegexPattern()
}
