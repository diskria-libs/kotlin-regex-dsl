package io.github.diskria.kotlin.regex.dsl.properties

import io.github.diskria.kotlin.regex.dsl.RegexPattern
import io.github.diskria.kotlin.regex.dsl.groups.NamedRegexGroup
import io.github.diskria.kotlin.regex.dsl.groups.RegexGroup
import io.github.diskria.kotlin.utils.properties.common.AbstractAutoNamedProperty

class AutoNamedRegexGroupProperty(val regexPattern: RegexPattern) : AbstractAutoNamedProperty<NamedRegexGroup>() {

    override fun mapToValue(propertyName: String): NamedRegexGroup =
        RegexGroup.ofNamed(propertyName, regexPattern)
}

fun RegexPattern.autoNamedRegexGroup(): AutoNamedRegexGroupProperty =
    AutoNamedRegexGroupProperty(this)
