package io.github.diskria.dsl.regex.extensions

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.groups.RegexGroup
import io.github.diskria.dsl.regex.primitives.RegexCharacterClass
import io.github.diskria.dsl.regex.ranges.RegexLatinLowercaseRange
import io.github.diskria.dsl.regex.ranges.RegexLatinUppercaseRange
import io.github.diskria.utils.kotlin.Constants
import io.github.diskria.utils.kotlin.extensions.collapseRepeating
import io.github.diskria.utils.kotlin.extensions.generics.foldChain

// region Template <lines = 8>
// operator fun MatchResult?.component{{ lineIndex + 1 }}(): MatchGroup? = this?.groups?.getOrNull({{ lineIndex }})
operator fun MatchResult?.component1(): MatchGroup? = this?.groups?.getOrNull(0)
operator fun MatchResult?.component2(): MatchGroup? = this?.groups?.getOrNull(1)
operator fun MatchResult?.component3(): MatchGroup? = this?.groups?.getOrNull(2)
operator fun MatchResult?.component4(): MatchGroup? = this?.groups?.getOrNull(3)
operator fun MatchResult?.component5(): MatchGroup? = this?.groups?.getOrNull(4)
operator fun MatchResult?.component6(): MatchGroup? = this?.groups?.getOrNull(5)
operator fun MatchResult?.component7(): MatchGroup? = this?.groups?.getOrNull(6)
operator fun MatchResult?.component8(): MatchGroup? = this?.groups?.getOrNull(7)
// endregion Template

@JvmInline
value class MatchGroupValues(val matchResult: MatchResult)

// region Template <lines = 7>
// operator fun MatchGroupValues.component{{ lineIndex + 1 }}(): String? = matchResult.component{{ lineIndex + 2 }}()?.value
operator fun MatchGroupValues.component1(): String? = matchResult.component2()?.value
operator fun MatchGroupValues.component2(): String? = matchResult.component3()?.value
operator fun MatchGroupValues.component3(): String? = matchResult.component4()?.value
operator fun MatchGroupValues.component4(): String? = matchResult.component5()?.value
operator fun MatchGroupValues.component5(): String? = matchResult.component6()?.value
operator fun MatchGroupValues.component6(): String? = matchResult.component7()?.value
operator fun MatchGroupValues.component7(): String? = matchResult.component8()?.value
// endregion Template

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

fun String.toSnakeCase(isUppercase: Boolean): String {
    val lowercaseCharacterClass = RegexCharacterClass.of(RegexLatinLowercaseRange)
    val uppercaseCharacterClass = RegexCharacterClass.of(RegexLatinUppercaseRange)
    val abbreviationsJoint = buildRegexPattern {
        append(RegexGroup.ofCaptured(uppercaseCharacterClass.oneOrMore()))
        append(
            RegexGroup.ofCaptured(
                buildRegexPattern {
                    append(uppercaseCharacterClass)
                    append(lowercaseCharacterClass)
                }
            )
        )
    }
    val wordsJoint = buildRegexPattern {
        append(RegexGroup.ofCaptured(lowercaseCharacterClass))
        append(RegexGroup.ofCaptured(uppercaseCharacterClass))
    }
    return this
        .replaceRegexes(abbreviationsJoint, wordsJoint) { _, (previousWordEnd, nextWordStart) ->
            previousWordEnd + Constants.Char.UNDERSCORE + nextWordStart
        }
        .collapseRepeating(Constants.Char.UNDERSCORE.toString())
        .trim(Constants.Char.UNDERSCORE)
        .run {
            if (isUppercase) uppercase()
            else lowercase()
        }
}
