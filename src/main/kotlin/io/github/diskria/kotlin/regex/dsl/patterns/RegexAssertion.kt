package io.github.diskria.kotlin.regex.dsl.patterns

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.extensions.toRegexPattern
import io.github.diskria.kotlin.utils.BracketsType
import io.github.diskria.kotlin.utils.Constants
import io.github.diskria.kotlin.utils.extensions.wrapWithBrackets

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
