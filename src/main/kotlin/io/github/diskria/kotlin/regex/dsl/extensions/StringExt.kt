package io.github.diskria.kotlin.regex.dsl.extensions

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.utils.MatchGroupValues
import io.github.diskria.kotlin.utils.extensions.generics.foldChain

fun String.findAll(regex: Regex): Sequence<Pair<String, MatchGroupValues>> =
    regex.findAll(this).map { it.value to MatchGroupValues(it) }

fun String.findSingleMatchGroupValuesOrNull(regex: Regex): MatchGroupValues? =
    regex.findAll(this).singleOrNull()?.let { MatchGroupValues(it) }

fun String.toRegexPattern(): RegexPattern =
    RegexPattern(this)

fun String.replaceRegexes(
    vararg regexPatterns: RegexPattern,
    transform: (String, MatchGroupValues) -> CharSequence,
): String =
    regexPatterns.toList().foldChain(this) { regexPattern -> replaceRegex(regexPattern, transform) }

fun String.replaceRegex(
    regexPattern: RegexPattern,
    transform: (String, MatchGroupValues) -> CharSequence,
): String =
    regexPattern.toRegex().replace(this) { matchResult ->
        transform(matchResult.value, MatchGroupValues(matchResult))
    }

fun String.replaceRegex(regexPattern: RegexPattern, transform: (String) -> CharSequence): String =
    replaceRegex(regexPattern) { wholeMatchValue, _ -> transform(wholeMatchValue) }

fun String.findAllRegexGroupValues(regex: Regex): Sequence<String> =
    regex.findAll(this).map { matchResult -> matchResult.value }
