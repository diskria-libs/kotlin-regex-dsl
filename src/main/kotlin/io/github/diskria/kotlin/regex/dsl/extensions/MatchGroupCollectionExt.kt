package io.github.diskria.kotlin.regex.dsl.extensions

fun MatchGroupCollection.getOrNull(index: Int): MatchGroup? =
    if (index in indices) get(index)
    else null
