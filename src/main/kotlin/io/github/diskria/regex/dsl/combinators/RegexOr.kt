package io.github.diskria.regex.dsl.combinators

import io.github.diskria.regex.dsl.RegexPattern
import io.github.diskria.regex.dsl.extensions.toRegexPattern
import io.github.diskria.regex.dsl.groups.RegexGroup
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.extensions.generics.joinToString

object RegexOr {

    fun of(vararg patterns: RegexPattern): RegexPattern =
        of(patterns.toList())

    fun of(patterns: List<RegexPattern>): RegexPattern =
        RegexGroup.of(
            patterns.joinToString(Constants.Char.VERTICAL_BAR).toRegexPattern(),
            isCaptured = false
        )
}
