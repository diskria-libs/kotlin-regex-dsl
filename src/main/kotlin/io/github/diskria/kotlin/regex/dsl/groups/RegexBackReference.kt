package io.github.diskria.kotlin.regex.dsl.groups

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.primitives.RegexNamedGroupBackReference
import io.github.diskria.kotlin.utils.BracketsType
import io.github.diskria.kotlin.utils.Constants
import io.github.diskria.kotlin.utils.extensions.common.failWithDetails
import io.github.diskria.kotlin.utils.extensions.common.failWithInvalidValue
import io.github.diskria.kotlin.utils.extensions.wrapWithBrackets
import io.github.diskria.kotlin.utils.properties.toAutoNamedProperty

object RegexBackReference {

    fun ofName(name: String): RegexPattern =
        if (RegexGroup.isInvalidName(name)) failWithInvalidValue(name)
        else RegexPattern(
            buildString {
                append(RegexNamedGroupBackReference)
                append(name.wrapWithBrackets(BracketsType.ANGLE))
            }
        )

    fun ofIndex(index: Int): RegexPattern {
        if (index <= 0) {
            failWithDetails("Regex group index can't be negative") {
                val index by index.toAutoNamedProperty()
                listOf(index)
            }
        }
        return RegexPattern(
            buildString {
                append(Constants.Char.BACK_SLASH)
                append(index)
            }
        )
    }
}
