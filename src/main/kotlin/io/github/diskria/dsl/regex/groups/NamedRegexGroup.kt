package io.github.diskria.dsl.regex.groups

import io.github.diskria.dsl.regex.RegexPattern

class NamedRegexGroup(
    val name: String,
    val pattern: RegexPattern,
    val backReference: RegexPattern,
)
