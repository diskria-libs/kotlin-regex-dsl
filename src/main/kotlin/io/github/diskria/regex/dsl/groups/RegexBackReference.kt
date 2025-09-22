package io.github.diskria.regex.dsl.groups

import io.github.diskria.regex.dsl.RegexPattern
import io.github.diskria.regex.dsl.primitives.RegexNamedGroupBackReference
import io.github.diskria.utils.kotlin.BracketsType
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.extensions.common.failWithDetails
import io.github.diskria.utils.kotlin.extensions.common.failWithInvalidValue
import io.github.diskria.utils.kotlin.extensions.wrapWithBrackets
import io.github.diskria.utils.kotlin.properties.toAutoNamedProperty

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
