package io.github.diskria.dsl.regex.groups

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.primitives.RegexNamedGroupBackReference
import io.github.diskria.utils.kotlin.BracketsType
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.delegates.toAutoNamedPair
import io.github.diskria.utils.kotlin.extensions.common.failWithDetails
import io.github.diskria.utils.kotlin.extensions.common.failWithInvalidValue
import io.github.diskria.utils.kotlin.extensions.common.unsupportedOperation
import io.github.diskria.utils.kotlin.extensions.wrapWithBrackets

object RegexBackReference {

    fun ofName(name: String): RegexPattern =
        if (RegexGroup.isInvalidName(name)) failWithInvalidValue(name)
        else newInstance(name, null)

    fun ofIndex(index: Int): RegexPattern {
        if (index <= 0) {
            failWithDetails("Regex group index can't be negative") {
                val index by index.toAutoNamedPair()
                listOf(index)
            }
        }
        return newInstance(null, index)
    }

    private fun newInstance(name: String? = null, index: Int? = null): RegexPattern =
        RegexPattern(
            buildString {
                when {
                    name != null -> {
                        append(RegexNamedGroupBackReference)
                        append(name.wrapWithBrackets(BracketsType.ANGLE))
                    }

                    index != null -> {
                        append(Constants.Char.BACK_SLASH)
                        append(index)
                    }

                    else -> unsupportedOperation()
                }
            }
        )
}
