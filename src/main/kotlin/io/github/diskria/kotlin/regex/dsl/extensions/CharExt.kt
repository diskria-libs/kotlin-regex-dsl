package io.github.diskria.kotlin.regex.dsl.extensions

import io.github.diskria.kotlin.regex.dsl.RegexPattern

fun Char.toRegexPattern(): RegexPattern =
    toString().toRegexPattern()
