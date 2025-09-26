package io.github.diskria.kotlin.regex.dsl.extensions

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.utils.extensions.generics.foldChain

operator fun MatchResult?.component1(): MatchGroup? = this?.groups?.getOrNull(0)
operator fun MatchResult?.component2(): MatchGroup? = this?.groups?.getOrNull(1)
operator fun MatchResult?.component3(): MatchGroup? = this?.groups?.getOrNull(2)
operator fun MatchResult?.component4(): MatchGroup? = this?.groups?.getOrNull(3)
operator fun MatchResult?.component5(): MatchGroup? = this?.groups?.getOrNull(4)
operator fun MatchResult?.component6(): MatchGroup? = this?.groups?.getOrNull(5)
operator fun MatchResult?.component7(): MatchGroup? = this?.groups?.getOrNull(6)
operator fun MatchResult?.component8(): MatchGroup? = this?.groups?.getOrNull(7)

@JvmInline
value class MatchGroupValues(val matchResult: MatchResult)

operator fun MatchGroupValues.component1(): String? = matchResult.component2()?.value
operator fun MatchGroupValues.component2(): String? = matchResult.component3()?.value
operator fun MatchGroupValues.component3(): String? = matchResult.component4()?.value
operator fun MatchGroupValues.component4(): String? = matchResult.component5()?.value
operator fun MatchGroupValues.component5(): String? = matchResult.component6()?.value
operator fun MatchGroupValues.component6(): String? = matchResult.component7()?.value
operator fun MatchGroupValues.component7(): String? = matchResult.component8()?.value

fun MatchGroupCollection.getOrNull(index: Int): MatchGroup? =
    if (index in indices) get(index)
    else null

fun String.findAll(regex: Regex): Sequence<Pair<String, MatchGroupValues>> =
    regex.findAll(this).map { it.value to MatchGroupValues(it) }

fun String.findSingleMatchGroupValuesOrNull(regex: Regex): MatchGroupValues? =
    regex.findAll(this).singleOrNull()?.let { MatchGroupValues(it) }

fun buildRegexPattern(builderAction: StringBuilder.() -> Unit): RegexPattern =
    buildString(builderAction).toRegexPattern()

fun String.toRegexPattern(): RegexPattern =
    RegexPattern(this)

fun Char.toRegexPattern(): RegexPattern =
    toString().toRegexPattern()

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
