package io.github.diskria.kotlin.regex.dsl.combinators

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.primitives.RegexCharacterClass
import io.github.diskria.kotlin.utils.extensions.primitives.regexEscaped

object RegexBetween {

    fun of(boundChar: Char): RegexPattern =
        RegexCharacterClass
            .ofNegated(boundChar)
            .zeroOrMore()
            .wrap(boundChar.regexEscaped())
}
