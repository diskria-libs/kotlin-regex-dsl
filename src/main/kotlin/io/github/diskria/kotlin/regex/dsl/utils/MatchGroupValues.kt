package io.github.diskria.kotlin.regex.dsl.utils

import io.github.diskria.kotlin.regex.dsl.extensions.*

@JvmInline
value class MatchGroupValues(val matchResult: MatchResult)

operator fun MatchGroupValues.component1(): String? = matchResult.component2()?.value
operator fun MatchGroupValues.component2(): String? = matchResult.component3()?.value
operator fun MatchGroupValues.component3(): String? = matchResult.component4()?.value
operator fun MatchGroupValues.component4(): String? = matchResult.component5()?.value
operator fun MatchGroupValues.component5(): String? = matchResult.component6()?.value
operator fun MatchGroupValues.component6(): String? = matchResult.component7()?.value
operator fun MatchGroupValues.component7(): String? = matchResult.component8()?.value
