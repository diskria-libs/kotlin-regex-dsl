package io.github.diskria.dsl.regex.combinators

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.extensions.toRegexPattern
import io.github.diskria.dsl.regex.groups.RegexGroup
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.extensions.generics.joinToString

object RegexOr {

    fun of(vararg patterns: RegexPattern): RegexPattern =
        of(patterns.toList())

    fun of(patterns: List<RegexPattern>): RegexPattern =
        RegexGroup.of(
            patterns.joinToString(Constants.Char.PIPE).toRegexPattern(),
            isCaptured = false
        )
}
