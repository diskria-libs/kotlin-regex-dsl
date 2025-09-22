package io.github.diskria.regex.dsl.primitives

import io.github.diskria.regex.dsl.RegexPattern

object RegexMetaSymbol {
    fun newInstance(char: Char): RegexPattern =
        RegexPattern(char.toString())
}

val RegexStartOfLine = RegexMetaSymbol.newInstance('^')
val RegexEndOfLine = RegexMetaSymbol.newInstance('$')
val RegexAnyChar = RegexMetaSymbol.newInstance('.')
val RegexZeroOrMore = RegexMetaSymbol.newInstance('*')
val RegexOneOrMore = RegexMetaSymbol.newInstance('+')
val RegexOptional = RegexMetaSymbol.newInstance('?')
val RegexAlternation = RegexMetaSymbol.newInstance('|')
