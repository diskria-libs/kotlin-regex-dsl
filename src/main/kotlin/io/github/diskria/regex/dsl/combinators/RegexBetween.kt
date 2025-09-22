package io.github.diskria.regex.dsl.combinators

import io.github.diskria.regex.dsl.RegexPattern
import io.github.diskria.regex.dsl.primitives.RegexCharacterClass
import io.github.diskria.utils.kotlin.extensions.primitives.regexEscaped

object RegexBetween {

    fun of(boundChar: Char): RegexPattern =
        RegexCharacterClass
            .ofNegated(boundChar)
            .zeroOrMore()
            .wrap(boundChar.regexEscaped())
}
