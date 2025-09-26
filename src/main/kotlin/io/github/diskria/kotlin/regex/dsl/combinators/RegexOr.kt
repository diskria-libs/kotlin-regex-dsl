package io.github.diskria.kotlin.regex.dsl.combinators

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.extensions.toRegexPattern
import io.github.diskria.kotlin.regex.dsl.groups.RegexGroup
import io.github.diskria.kotlin.utils.Constants
import io.github.diskria.kotlin.utils.extensions.generics.joinToString

object RegexOr {

    fun of(vararg patterns: RegexPattern): RegexPattern =
        of(patterns.toList())

    fun of(patterns: List<RegexPattern>): RegexPattern =
        RegexGroup.of(
            patterns.joinToString(Constants.Char.VERTICAL_BAR).toRegexPattern(),
            isCaptured = false
        )
}
