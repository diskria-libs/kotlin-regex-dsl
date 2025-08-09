package io.github.diskria.dsl.regex.delegates

import io.github.diskria.dsl.regex.RegexPattern
import io.github.diskria.dsl.regex.groups.NamedRegexGroup
import io.github.diskria.dsl.regex.groups.RegexGroup
import io.github.diskria.utils.kotlin.delegates.AutoNamedProperty

class AutoNamedRegexGroupProperty(regexPattern: RegexPattern) : AutoNamedProperty<NamedRegexGroup>(
    { propertyName -> RegexGroup.ofNamed(propertyName, regexPattern) }
)

fun RegexPattern.toAutoNamedRegexGroup(): AutoNamedRegexGroupProperty =
    AutoNamedRegexGroupProperty(this)
