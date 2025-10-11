package io.github.diskria.kotlin.regex.dsl.extensions.common

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.extensions.toRegexPattern

fun buildRegexPattern(builderAction: StringBuilder.() -> Unit): RegexPattern =
    buildString(builderAction).toRegexPattern()
