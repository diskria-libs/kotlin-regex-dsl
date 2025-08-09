package io.github.diskria.dsl.regex.combinators

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.primitives.RegexCharacterClass
import io.github.diskria.utils.kotlin.extensions.primitives.regexEscaped

object RegexBetween {

    fun of(boundChar: Char): RegexPattern =
        RegexCharacterClass
            .ofNegated(boundChar)
            .zeroOrMore()
            .wrap(boundChar.regexEscaped())
}
