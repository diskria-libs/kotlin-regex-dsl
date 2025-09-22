package io.github.diskria.regex.dsl.groups

import io.github.diskria.regex.dsl.RegexPattern

class NamedRegexGroup(
    val name: String,
    val pattern: RegexPattern,
    val backReference: RegexPattern,
)
