package io.github.diskria.kotlin.regex.dsl.groups

import io.github.diskria.kotlin.regex.dsl.RegexPattern

class NamedRegexGroup(
    val name: String,
    val pattern: RegexPattern,
    val backReference: RegexPattern,
)
