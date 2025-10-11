package io.github.diskria.kotlin.regex.dsl.extensions

operator fun MatchResult?.component1(): MatchGroup? = this?.groups?.getOrNull(0)
operator fun MatchResult?.component2(): MatchGroup? = this?.groups?.getOrNull(1)
operator fun MatchResult?.component3(): MatchGroup? = this?.groups?.getOrNull(2)
operator fun MatchResult?.component4(): MatchGroup? = this?.groups?.getOrNull(3)
operator fun MatchResult?.component5(): MatchGroup? = this?.groups?.getOrNull(4)
operator fun MatchResult?.component6(): MatchGroup? = this?.groups?.getOrNull(5)
operator fun MatchResult?.component7(): MatchGroup? = this?.groups?.getOrNull(6)
operator fun MatchResult?.component8(): MatchGroup? = this?.groups?.getOrNull(7)
